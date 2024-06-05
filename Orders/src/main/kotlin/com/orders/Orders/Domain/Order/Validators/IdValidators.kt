package com.orders.Orders.Domain.Order.Validators

import com.orders.Orders.Domain.Order.Exceptions.IdException

class IdValidators {
    companion object {
        fun validateId(id: Int): Int {
            if (id <= 0) {
                throw IdException("Id should a non-negative number")
            }
            return id
        }
    }
}