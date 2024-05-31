package com.authorization.Authorization.Infrastructure.User

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserModel, Int> {
    @Query(
        value = "SELECT * FROM user WHERE email = ?",
        nativeQuery = true
    )
    fun findUserByEmail(email: String): UserModel?

    @Query(
        value = "SELECT * FROM user WHERE nickname = ?",
        nativeQuery = true
    )
    fun findUserByNickName(nickName: String?): UserModel?
}