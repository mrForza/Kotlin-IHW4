package com.orders.Orders.Domain.Station.Validators

import com.orders.Orders.Domain.Station.Exceptions.NameException

class NameValidator {
    companion object {
        fun validateName(name: String): String {
            if (!name.contains("([a-zA-Z0-9]{3}-){2}[\\d]".toRegex())) {
                throw NameException("Incorrect format of station's name")
            }
            return name
        }
    }
}