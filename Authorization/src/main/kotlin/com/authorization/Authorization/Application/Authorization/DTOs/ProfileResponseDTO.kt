package com.authorization.Authorization.Application.Authorization.DTOs

data class ProfileResponseDTO(
    val nickname: String,
    val email: String,
    val name: String,
    val surname: String,
    val age: Int
)