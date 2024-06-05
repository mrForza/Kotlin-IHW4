package com.orders.Orders.Domain.Order.ValueObjects

import com.orders.Orders.Domain.Base.BaseValueObject
import com.orders.Orders.Domain.Order.Validators.IdValidators

class FromStationId(rawStationId: Int) : BaseValueObject() {
    private val fromStationId = IdValidators.validateId(rawStationId)
    init {
        atomicObjects.add(fromStationId)
    }

    override fun toString(): String {
        return "From station id: $fromStationId"
    }
}