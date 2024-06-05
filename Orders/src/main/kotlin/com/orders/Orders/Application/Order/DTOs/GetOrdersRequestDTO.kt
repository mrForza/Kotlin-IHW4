package com.orders.Orders.Application.Order.DTOs

data class GetOrdersRequestDTO(
    val orders: List<GetOrderResponseDTO?>
)