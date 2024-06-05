package com.orders.Orders.Presentation

import com.orders.Orders.Application.Order.DTOs.CreateOrderRequestDTO
import com.orders.Orders.Application.Order.OrderService
import jakarta.servlet.http.HttpServletRequest
import org.apache.coyote.Response
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

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
            return ResponseEntity<Any>(
                exception.message,
                HttpStatusCode.valueOf(401)
            )
        }
    }

    @GetMapping("/orders/{id}/")
    fun getOrder(request: HttpServletRequest, @PathVariable id: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                orderService.getOrder(request, id),
                HttpStatusCode.valueOf(200)
            )
        } catch (exception: Exception) {
            return ResponseEntity<Any>(
                exception.message,
                HttpStatusCode.valueOf(401)
            )
        }
    }

    @PostMapping("/orders/create/")
    fun createOrder(request: HttpServletRequest, createOrderRequestDTO: CreateOrderRequestDTO): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                orderService.createOrder(request, createOrderRequestDTO),
                HttpStatusCode.valueOf(201)
            )
        } catch (exception: Exception) {
            return ResponseEntity<Any>(
                exception.message,
                HttpStatusCode.valueOf(401)
            )
        }
    }
}