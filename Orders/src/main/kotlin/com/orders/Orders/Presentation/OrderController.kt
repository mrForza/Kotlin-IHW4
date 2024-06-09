package com.orders.Orders.Presentation

import com.orders.Orders.Application.Order.DTOs.CreateOrderRequestDTO
import com.orders.Orders.Application.Order.Exceptions.IncorrectOrderIdException
import com.orders.Orders.Application.Order.Exceptions.IncorrectStationIdException
import com.orders.Orders.Application.Order.Exceptions.NoAuthenticatedException
import com.orders.Orders.Application.Order.OrderService
import com.orders.Orders.Domain.Order.Exceptions.Base.BaseOrderException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class OrderController(private val orderService: OrderService) {
    @GetMapping("/orders/")
    fun getOrders(request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                orderService.getOrders(request),
                HttpStatusCode.valueOf(200)
            )
        } catch (exception: Exception) {
            when (exception) {
                is NoAuthenticatedException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(401))
                }
                else -> return ResponseEntity<Any>(
                    "Something went wrong! Please, check your authorization credentials",
                    HttpStatusCode.valueOf(400)
                )
            }
        }
    }

    @GetMapping("/orders/{id}/")
    fun getOrder(@PathVariable id: Int, request: HttpServletRequest, ): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                orderService.getOrder(request, id),
                HttpStatusCode.valueOf(200)
            )
        } catch (exception: Exception) {
            when (exception) {
                is IncorrectOrderIdException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
                }
                is NoAuthenticatedException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(401))
                }
                else -> return ResponseEntity<Any>(
                    "Something went wrong! Please, check your authorization credentials",
                    HttpStatusCode.valueOf(400)
                )
            }
        }
    }

    @PostMapping("/orders/create/")
    fun createOrder(@RequestBody createOrderRequestDTO: CreateOrderRequestDTO,
                    request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                orderService.createOrder(request, createOrderRequestDTO),
                HttpStatusCode.valueOf(201)
            )
        } catch (exception: Exception) {
            when (exception) {
                is BaseOrderException, is IncorrectStationIdException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
                }
                is NoAuthenticatedException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(401))
                }
                else -> return ResponseEntity<Any>(
                    "Something went wrong! Please, check your authorization credentials",
                    HttpStatusCode.valueOf(400)
                )
            }
        }
    }
}