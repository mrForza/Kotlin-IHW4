package com.authorization.Authorization.Application.Authorization.DTOs

data class RegistrationResponseDTO(
    val id: Int,
    val name: String,
    val surname: String,
    val message: String = "You have been successfully registered!"
)