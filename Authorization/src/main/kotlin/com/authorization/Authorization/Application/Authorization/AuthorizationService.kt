package com.authorization.Authorization.Application.Authorization

import com.authorization.Authorization.Application.Authorization.DTOs.JwtDTO
import com.authorization.Authorization.Application.Authorization.DTOs.LoginRequestDTO
import com.authorization.Authorization.Application.Authorization.DTOs.LoginResponseDTO
import com.authorization.Authorization.Application.Authorization.DTOs.RegistrationRequestDTO
import com.authorization.Authorization.Domain.User.Validators.PasswordValidator
import com.authorization.Authorization.Infrastructure.UserModel
import com.authorization.Authorization.Infrastructure.UserRepository
import com.authorization.Authorization.Infrastructure.UserRole
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.log

@Service
class AuthorizationService(
    private val userRepo: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) {

    fun checkNickNameAndEmailExistence(nickName: String, email: String) {
        if (userRepo.findUserByNickName(nickName) != null) {
            throw AuthorizationException("The user with this nickname already exists")
        }

        if (userRepo.findUserByEmail(email) != null) {
            throw AuthorizationException("The user with this email already exists")
        }
    }

    fun register(registrationDTO: RegistrationRequestDTO): JwtDTO  {
        val user = UserModel(
            id = null,
            nickname = registrationDTO.nickName,
            email = registrationDTO.email,
            password = BCryptPasswordEncoder().encode(PasswordValidator.validatePassword(registrationDTO.password)),
            name = registrationDTO.name,
            surname = registrationDTO.surname,
            age = registrationDTO.age,
            created = Date(),
            role = UserRole.USER.toString()
        )

        userRepo.save(user)
        return JwtDTO(jwtService.generateToken(user))
    }

    fun authenticate(loginDTO : LoginRequestDTO): LoginResponseDTO {
        println("################################## AUTHENTICATION")
        val data = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userRepo.findUserByEmail(loginDTO.email)?.getNickName(),
                loginDTO.password
            )
        )
        println(data.isAuthenticated)
        println("################################## SUCCESS")

        return LoginResponseDTO()
    }
}