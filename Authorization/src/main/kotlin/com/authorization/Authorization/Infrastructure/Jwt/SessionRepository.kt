package com.authorization.Authorization.Infrastructure.Jwt

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SessionRepository : CrudRepository<SessionModel, Int> {
    @Query(
        value = "SELECT * FROM session WHERE user_id = ?",
        nativeQuery = true
    )
    fun findSessionModelByUserId(userId: Int): SessionModel?

    @Query(
        value = "SELECT * FROM session WHERE token = ? LIMIT 1",
        nativeQuery = true
    )
    fun findSessionModelByToken(token: String): SessionModel?
}