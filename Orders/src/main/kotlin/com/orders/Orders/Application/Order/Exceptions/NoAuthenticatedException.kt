package com.orders.Orders.Application.Order.Exceptions

import com.orders.Orders.Application.Order.Exceptions.Base.BaseOrderApplicationException

class NoAuthenticatedException(message: String) : BaseOrderApplicationException(message) {
}