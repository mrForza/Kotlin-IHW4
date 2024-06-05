package com.orders.Orders.Domain.Order.ValueObjects

import com.orders.Orders.Domain.Base.BaseValueObject
import com.orders.Orders.Domain.Order.Validators.IdValidators

class UserId(rawUserId: Int) : BaseValueObject() {
    private val userId = IdValidators.validateId(rawUserId)
    init {
        atomicObjects.add(userId)
    }

    override fun toString(): String {
        return "User id: $userId"
    }
}