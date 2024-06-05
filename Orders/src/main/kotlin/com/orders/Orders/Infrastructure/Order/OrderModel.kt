package com.orders.Orders.Infrastructure.Order

import com.orders.Orders.Domain.Order.OrderEntity
import com.orders.Orders.Infrastructure.Station.StationModel
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
@Table(name = "order")
class OrderModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(length = 64) val name: String,
    @NotNull val userId: Int,
    @OneToOne
    @JoinColumn(name = "from_station_id") val fromStationId: StationModel,
    @OneToOne
    @JoinColumn(name = "to_station_id") val toStationId: StationModel,
    @NotNull @Column(length = 32) val status: String,
    @NotNull val createdAt: Date
) {
    constructor() : this(null, "", 0, StationModel(), StationModel(), "", Date())

    companion object {
        fun convertModelToEntity(orderModeL: OrderModel): OrderEntity {
            return OrderEntity(
                orderModeL.id,
                orderModeL.name,
                orderModeL.userId,
                orderModeL.fromStationId.id!!,
                orderModeL.toStationId.id!!,
                orderModeL.status,
                orderModeL.createdAt
            )
        }
    }
}