package com.orders.Orders.Infrastructure.Station

import jakarta.persistence.*

@Entity
@Table(name = "station")
class StationModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(unique = true, length = 64) val name: String
) {
    constructor() : this(null, "") {

    }
}