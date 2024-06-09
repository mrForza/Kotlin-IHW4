package com.orders.Orders.Application.Order.Exceptions

import com.orders.Orders.Application.Order.Exceptions.Base.BaseOrderApplicationException

class IncorrectStationIdException(message: String) : BaseOrderApplicationException(message) {
}