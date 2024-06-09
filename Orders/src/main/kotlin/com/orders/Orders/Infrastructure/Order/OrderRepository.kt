package com.orders.Orders.Infrastructure.Order

import com.orders.Orders.Domain.Order.ValueObjects.FromStationId
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.Date

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

    @Transactional
    @Modifying
    @Query(
        value = "INSERT INTO \"order\"" +
                "(id, name, user_id, from_station_id, to_station_id, status, created_at)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",
        nativeQuery = true
    )
   fun create(id: Int, name: String, userId: Int, fromStationId: Int, toStationId: Int, status: String, createdAt: Timestamp)

    @Transactional
    @Modifying
    @Query(
        value = "UPDATE \"order\" SET status = ? WHERE id = ?",
        nativeQuery = true
    )
    fun changeStatus(status: String, id: Int, )

   @Query(
       value = "SELECT MAX(id) FROM \"order\"",
       nativeQuery = true
   )
    fun getMaxId(): Int?
}