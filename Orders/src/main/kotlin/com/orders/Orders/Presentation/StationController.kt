package com.orders.Orders.Presentation

import com.orders.Orders.Application.Station.StationService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class StationController(private val stationService: StationService) {
    @GetMapping("/stations/")
    fun getStations(): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                stationService.getStations(),
                HttpStatusCode.valueOf(200)
            )
        } catch (exception: Exception) {
            return ResponseEntity<Any>(
                "Something went wrong! Check your credentials",
                HttpStatusCode.valueOf(400)
            )
        }
    }
}