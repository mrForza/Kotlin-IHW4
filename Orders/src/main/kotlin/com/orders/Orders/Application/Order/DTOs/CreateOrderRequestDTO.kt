package com.orders.Orders.Application.Order.DTOs

data class CreateOrderRequestDTO(
    val name: String,
    val userId: Int,
    val fromStationId: Int,
    val toStationId: Int,
)