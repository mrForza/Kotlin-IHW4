package com.orders.Orders.Domain.Order.ValueObjects

import com.orders.Orders.Domain.Base.BaseValueObject
import com.orders.Orders.Domain.Order.Validators.IdValidators

class ToStationId(rawToStationId: Int) : BaseValueObject() {
    private val toStationId = IdValidators.validateId(rawToStationId)
    init {
        atomicObjects.add(toStationId)
    }

    override fun toString(): String {
        return "To station id: $toStationId"
    }
}