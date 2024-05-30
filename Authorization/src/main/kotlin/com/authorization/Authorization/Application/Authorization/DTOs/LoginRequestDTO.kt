package com.authorization.Authorization.Application.Authorization.DTOs

data class LoginRequestDTO(
    val email: String,
    val password: String
)