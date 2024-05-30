package com.authorization.Authorization.Domain.User

import com.authorization.Authorization.Domain.Base.BaseEntity
import com.authorization.Authorization.Domain.User.ValueObjects.*
import java.util.Date

open class UserEntity(
    id: Int?,
    rawNickName: String,
    rawEmail: String,
    rawPassword: String,
    rawName: String,
    rawSurname: String,
    rawAge: Int,
    private val createdAt: Date
) : BaseEntity(id) {

    private val nickName = NickName(rawNickName)

    private var email = Email(rawEmail)

    private var password = Password(rawPassword)

    private var name = Name(rawName)

    private var surname = Surname(rawSurname)

    private var age = Age(rawAge)

    fun getEmail(): String {
        return email.email
    }

    override fun toString(): String {
        return """
            User: $nickName
            $email
            $password
            $name
            $surname
            $age
            $createdAt
        """.trimIndent()
    }
}
