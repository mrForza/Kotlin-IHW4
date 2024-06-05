package com.orders.Orders.Domain.Order.Validators

import com.orders.Orders.Domain.Order.Exceptions.StatusException
import com.orders.Orders.Domain.Order.Status

class StatusValidator {
    companion object {
        fun validateStatus(status: String): Status {
            try {
                return Status.valueOf(status)

            } catch (exc: IllegalArgumentException) {
                throw StatusException("Incorrect status was given!")
            }
        }
    }
}