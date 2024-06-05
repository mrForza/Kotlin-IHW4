package com.orders.Orders.Domain.Order

import com.orders.Orders.Domain.Base.BaseEntity
import com.orders.Orders.Domain.Order.ValueObjects.FromStationId
import com.orders.Orders.Domain.Order.ValueObjects.Status
import com.orders.Orders.Domain.Order.ValueObjects.ToStationId
import com.orders.Orders.Domain.Order.ValueObjects.UserId
import java.util.Date

class OrderEntity(
    id: Int?,
    rawName: String,
    rawUserId: Int,
    rawFromStationId: Int,
    rawToStationId: Int,
    rawStatus: String,
    private val createdAt: Date
) : BaseEntity(id) {

    private val name = rawName

    private val userId = UserId(rawUserId)

    private val fromStationId = FromStationId(rawFromStationId)

    private val toStationId = ToStationId(rawToStationId)

    private val status = Status(rawStatus)

    override fun toString(): String {
        return """
            Order №$id
                $name
                $userId
                $fromStationId
                $toStationId
                $status
        """.trimIndent()
    }
}