package com.orders.Orders.Domain.Order.ValueObjects

import com.orders.Orders.Domain.Base.BaseValueObject
import com.orders.Orders.Domain.Order.Validators.StatusValidator

class Status(rawStatusString: String) : BaseValueObject() {
    private val status = StatusValidator.validateStatus(rawStatusString)
    init {
        atomicObjects.add(status)
    }

    override fun toString(): String {
        return "Status: ${status.name}"
    }
}