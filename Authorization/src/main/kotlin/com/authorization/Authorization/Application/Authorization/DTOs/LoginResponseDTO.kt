package com.authorization.Authorization.Application.Authorization.DTOs

class LoginResponseDTO(
    val token: String,
    val message: String = "You have been successfully authenticated!"
)