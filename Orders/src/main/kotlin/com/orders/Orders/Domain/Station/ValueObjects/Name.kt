package com.orders.Orders.Domain.Station.ValueObjects

import com.orders.Orders.Domain.Base.BaseValueObject
import com.orders.Orders.Domain.Station.Validators.NameValidator

class Name(rawName: String) : BaseValueObject() {
    private val name = NameValidator.validateName(rawName)
    init {
        atomicObjects.add(name)
    }

    override fun toString(): String {
        return "Name: $name"
    }
}