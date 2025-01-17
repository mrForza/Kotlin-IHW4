package com.authorization.Authorization.Presentation

import com.authorization.Authorization.Application.Authorization.AuthorizationService
import com.authorization.Authorization.Application.Authorization.DTOs.LoginRequestDTO
import com.authorization.Authorization.Application.Authorization.DTOs.RegistrationRequestDTO
import com.authorization.Authorization.Application.Authorization.Exceptions.Base.BaseAuthorizationException
import com.authorization.Authorization.Domain.User.Exceptions.Base.BaseUserException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:8080")
class AuthorizationController(
    private val authorizationService: AuthorizationService
) {
    @PostMapping("/register/")
    fun register(@RequestBody registrationRequestDTO: RegistrationRequestDTO): ResponseEntity<Any> {
        return try {
            authorizationService.checkNickNameAndEmailExistence(
                registrationRequestDTO.nickName,
                registrationRequestDTO.email
            )
            ResponseEntity<Any>(
                authorizationService.register(registrationRequestDTO).message,
                HttpStatusCode.valueOf(201)
            )
        } catch (exception: Exception) {
            when (exception) {
                is BaseUserException, is BaseAuthorizationException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
                }
                else -> return ResponseEntity<Any>(
                    "Something went wrong! Please, check your authorization credentials",
                    HttpStatusCode.valueOf(400)
                )
            }
        }
    }

    @GetMapping("/profile/")
    fun profile(request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                authorizationService.getProfile(request),
                HttpStatusCode.valueOf(200)
            )
        } catch (exception: Exception) {
            when (exception) {
                is BaseAuthorizationException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
                }
                else -> return ResponseEntity<Any>(
                    "Something went wrong! Please, check your authorization credentials",
                    HttpStatusCode.valueOf(400)
                )
            }
        }
    }

    @PostMapping("/login/")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                authorizationService.authenticate(loginRequestDTO),
                HttpStatusCode.valueOf(201)
            )
        } catch (exception: Exception) {
            when (exception) {
                is BaseAuthorizationException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
                }
                else -> return ResponseEntity<Any>(
                    "Something went wrong! Please, check your authorization credentials",
                    HttpStatusCode.valueOf(400)
                )
            }
        }
    }

    @GetMapping("/logout/")
    fun logout(request: HttpServletRequest): ResponseEntity<Any> {
        return try  {
            ResponseEntity<Any>(authorizationService.logout(request), HttpStatusCode.valueOf(200))
        } catch (exception: Exception) {
            when (exception) {
                is BaseAuthorizationException -> {
                    return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
                }
                else -> return ResponseEntity<Any>(
                    "Something went wrong! Please, check your authorization credentials",
                    HttpStatusCode.valueOf(400)
                )
            }
        }
    }

    @GetMapping("/check/")
    fun checkUser(request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(
                authorizationService.checkUserCredentials(request),
                HttpStatusCode.valueOf(200)
            )
        } catch (exception: Exception) {
            return ResponseEntity<Any>(
                "Something went wrong! Please, check your authorization credentials",
                HttpStatusCode.valueOf(400)
            )
        }
    }
}