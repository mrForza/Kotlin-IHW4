package com.orders.Orders.Application.Order.DTOs

data class GetOrderResponseDTO(
    val id: Int,
    val name: String,
    val fromStationId: Int,
    val toStationId: Int,
    val status: String
)