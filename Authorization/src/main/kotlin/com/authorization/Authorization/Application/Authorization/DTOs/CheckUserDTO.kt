package com.authorization.Authorization.Application.Authorization.DTOs

data class CheckUserDTO(
    val success: Boolean,
    val userId: Int?
)