package com.orders.Orders.Application.Station

import com.orders.Orders.Application.Station.DTOs.GetStationRequestDTO
import com.orders.Orders.Application.Station.DTOs.GetStationsRequestDTO
import com.orders.Orders.Infrastructure.Station.StationRepository
import org.springframework.stereotype.Service

@Service
class StationService(private val stationRepository: StationRepository) {
    fun getStations(): GetStationsRequestDTO {
        val stationModels = stationRepository.findAll()
        val stationDTOs = mutableListOf<GetStationRequestDTO>()

        for (station in stationModels) {
            station.id?.let {
                GetStationRequestDTO(
                    it,
                    station.name
                )
            }?.let { stationDTOs.add(it) }
        }

        return GetStationsRequestDTO(stationDTOs)
    }
}