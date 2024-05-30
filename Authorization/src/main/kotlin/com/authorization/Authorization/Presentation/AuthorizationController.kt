package com.authorization.Authorization.Presentation

import com.authorization.Authorization.Application.Authorization.AuthorizationException
import com.authorization.Authorization.Application.Authorization.AuthorizationService
import com.authorization.Authorization.Application.Authorization.DTOs.LoginRequestDTO
import com.authorization.Authorization.Application.Authorization.DTOs.LoginResponseDTO
import com.authorization.Authorization.Application.Authorization.DTOs.RegistrationRequestDTO
import com.authorization.Authorization.Application.Authorization.DTOs.RegistrationResponseDTO
import com.authorization.Authorization.Application.User.UserService
import com.authorization.Authorization.Domain.User.Exceptions.Base.BaseUserException
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorizationController(
    private val authorizationService: AuthorizationService,
    private val userService: UserService
) {

    @PostMapping("/register/")
    fun register(@RequestBody registrationRequestDTO: RegistrationRequestDTO): ResponseEntity<Any> {
        var jwtToken = ""
        try {
            authorizationService.checkNickNameAndEmailExistence(
                registrationRequestDTO.nickName,
                registrationRequestDTO.email
            )
            jwtToken = authorizationService.register(registrationRequestDTO).token
        } catch (exception: BaseUserException) {
            return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
        } catch (exception: AuthorizationException) {
            return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
        }
        return ResponseEntity<Any>(RegistrationResponseDTO(jwtToken), HttpStatusCode.valueOf(201))
    }

    @PostMapping("/login/")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                authorizationService.authenticate(loginRequestDTO),
                HttpStatusCode.valueOf(201)
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            ResponseEntity<Any>("${exception.message}", HttpStatusCode.valueOf(400))
        }
    }

    @GetMapping("/logout/")
    fun logout(): ResponseEntity<Any> {
        return ResponseEntity<Any>("logout!", HttpStatusCode.valueOf(200))
    }
}