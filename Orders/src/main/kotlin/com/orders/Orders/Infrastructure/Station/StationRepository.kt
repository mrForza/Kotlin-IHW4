package com.orders.Orders.Infrastructure.Station

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StationRepository : CrudRepository<StationModel, Int> {

}