package com.authorization.Authorization.Application.Authorization.DTOs

data class RegistrationRequestDTO(
    val nickName: String,
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val age: Int
)