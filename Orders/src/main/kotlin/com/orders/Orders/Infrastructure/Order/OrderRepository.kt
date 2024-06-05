package com.orders.Orders.Infrastructure.Order

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<OrderModel, Int>{
    @Query(
        value = "SELECT * FROM \"order\" WHERE user_id = ?",
        nativeQuery = true
    )
    fun getOrdersByUserId(userId: Int) : List<OrderModel>

    @Query(
        value = "SELECT * FROM \"order\" WHERE user_id = ? AND id = ? LIMIT 1",
        nativeQuery = true
    )
    fun getOrderByUserIdAndId(userId: Int, id: Int): OrderModel?
}