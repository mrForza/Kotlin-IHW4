package com.orders.Orders.Application.Station.DTOs

data class GetStationsRequestDTO (
    val stations: MutableList<GetStationRequestDTO>
)