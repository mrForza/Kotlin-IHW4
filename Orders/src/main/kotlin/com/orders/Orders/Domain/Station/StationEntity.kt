package com.orders.Orders.Domain.Station

import com.orders.Orders.Domain.Base.BaseEntity
import com.orders.Orders.Domain.Station.ValueObjects.Name

class StationEntity(
    id: Int?,
    rawName: String
) : BaseEntity(id) {
    private val name = Name(rawName)

    override fun toString(): String {
        return """
            Station №$id
            $name
        """.trimIndent()
    }
}