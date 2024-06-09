package com.orders.Orders.Application.Order

import com.orders.Orders.Application.Order.DTOs.CreateOrderRequestDTO
import com.orders.Orders.Application.Order.DTOs.CreateOrderResponseDTO
import com.orders.Orders.Application.Order.DTOs.GetOrderResponseDTO
import com.orders.Orders.Application.Order.DTOs.GetOrdersRequestDTO
import com.orders.Orders.Application.Order.Exceptions.IncorrectOrderIdException
import com.orders.Orders.Application.Order.Exceptions.IncorrectStationIdException
import com.orders.Orders.Application.Order.Exceptions.NoAuthenticatedException
import com.orders.Orders.Infrastructure.MicroServiceCommunication.AuthServiceAdapter
import com.orders.Orders.Infrastructure.Order.OrderModel
import com.orders.Orders.Infrastructure.Order.OrderRepository
import com.orders.Orders.Infrastructure.Station.StationRepository
import jakarta.servlet.http.HttpServletRequest
import org.apache.catalina.util.IOTools.flow
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val stationRepository: StationRepository,
    private val authAdapter: AuthServiceAdapter) {

    fun getOrders(request: HttpServletRequest): GetOrdersRequestDTO {
        val data = authAdapter.checkUserAuthorization(request).toString()
        if (data.isNotEmpty()) {
            val isAuthenticated = data.split(",")[0].split(":")[1].toBoolean()

            if (isAuthenticated) {
                val userId = data.split(",")[1].split(":")[1].replace("}", "").toInt()
                val orderModels = orderRepository.getOrdersByUserId(userId)
                val orderDTOs = mutableListOf<GetOrderResponseDTO>()

                for (orderModel in orderModels) {
                    if (orderModel.id != null && orderModel.fromStationId.id != null && orderModel.toStationId.id != null) {
                        orderDTOs.add(GetOrderResponseDTO(
                            orderModel.id,
                            orderModel.name,
                            orderModel.fromStationId.id,
                            orderModel.toStationId.id,
                            orderModel.status
                        ))
                    }
                }

                return GetOrdersRequestDTO(orderDTOs)
            }
        }

        throw NoAuthenticatedException("You are not authenticated!")
    }

    fun getOrder(request: HttpServletRequest, id: Int): GetOrderResponseDTO {
        val data = authAdapter.checkUserAuthorization(request).toString()
        if (data.isNotEmpty()) {
            val isAuthenticated = data.split(",")[0].split(":")[1].toBoolean()

            if (isAuthenticated) {
                val userId = data.split(",")[1].split(":")[1].replace("}", "").toInt()
                val orderModel = orderRepository.getOrderByUserIdAndId(userId, id)
                    ?: throw IncorrectOrderIdException("There is no order with this id")

                return GetOrderResponseDTO(
                    orderModel.id!!,
                    orderModel.name,
                    orderModel.fromStationId.id!!,
                    orderModel.toStationId.id!!,
                    orderModel.status
                )
            }
        }

        throw NoAuthenticatedException("You are not authenticated!")
    }

    fun createOrder(request: HttpServletRequest, createOrderRequestDTO: CreateOrderRequestDTO): CreateOrderResponseDTO {
        val data = authAdapter.checkUserAuthorization(request).toString()
        val isAuthenticated = data.split(",")[0].split(":")[1].toBoolean()

        if (isAuthenticated) {
            val userId = data.split(",")[1].split(":")[1].replace("}", "").toInt()
            val fromStation = stationRepository.findById(createOrderRequestDTO.fromStationId)
            val toStation = stationRepository.findById(createOrderRequestDTO.toStationId)

            if (fromStation.isEmpty) {
                throw IncorrectStationIdException("Incorrect id of From-Station")
            }
            if (toStation.isEmpty) {
                throw IncorrectStationIdException("Incorrect id of To-Station")
            }

            val orderModel = OrderModel(
                null,
                createOrderRequestDTO.name,
                userId,
                fromStation.get(),
                toStation.get(),
                "check",
                Date()
            )

            val orderEntity = OrderModel.convertModelToEntity(orderModel) // Domain logic validation
            val maxId = orderRepository.getMaxId() ?: 1
            orderRepository.create(
                maxId + 1,
                orderModel.name,
                orderModel.userId,
                orderModel.fromStationId.id!!,
                orderModel.toStationId.id!!,
                orderModel.status,
                Timestamp(orderModel.createdAt.time)
            )

            if (Random().nextBoolean()) {
                orderRepository.changeStatus("success", maxId + 1)
            } else {
                orderRepository.changeStatus("reject", maxId + 1)
            }

            return CreateOrderResponseDTO("check")
        }

        throw NoAuthenticatedException("You are not authenticated!")
    }
}