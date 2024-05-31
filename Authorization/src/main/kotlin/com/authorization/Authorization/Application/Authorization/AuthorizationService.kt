package com.authorization.Authorization.Application.Authorization

import com.authorization.Authorization.Application.Authorization.DTOs.*
import com.authorization.Authorization.Infrastructure.Jwt.SessionModel
import com.authorization.Authorization.Infrastructure.Jwt.SessionRepository
import com.authorization.Authorization.Infrastructure.User.UserModel
import com.authorization.Authorization.Infrastructure.User.UserRepository
import com.authorization.Authorization.Infrastructure.User.UserRole
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*

@Service
class AuthorizationService(
    private val userRepo: UserRepository,
    private val sessionRepo: SessionRepository,
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

    fun register(registrationDTO: RegistrationRequestDTO): RegistrationResponseDTO  {
        val user = UserModel(
            id = null,
            nickname = registrationDTO.nickName,
            email = registrationDTO.email,
            password = registrationDTO.password,
            name = registrationDTO.name,
            surname = registrationDTO.surname,
            age = registrationDTO.age,
            created = Date(),
            role = UserRole.USER.toString()
        )

        UserModel.convertModelEntityToDomainEntity(user) // Check data correctness
        user.password = UserModel.hashPassword(user.password) // Hash the password
        userRepo.save(user)
        return RegistrationResponseDTO(
            user.id ?: 0,
            user.name,
            user.surname
        )
    }

    fun authenticate(loginDTO : LoginRequestDTO): LoginResponseDTO {
        val data = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userRepo.findUserByEmail(loginDTO.email)?.nickname,
                loginDTO.password
            )
        )

        val user = userRepo.findUserByEmail(data.name) ?: throw Exception("No user found with this email!")
        val jwtToken = jwtService.generateToken(user)

        sessionRepo.save(SessionModel(
            id = null,
            user = user,
            token = jwtToken,
            expires = Timestamp(jwtService.extractAllClaims(jwtToken).expiration.time)
        ))
        return LoginResponseDTO(jwtToken)
    }

    fun getProfile(request: HttpServletRequest): ProfileResponseDTO {
        val jwtToken = request.getHeader("Authorization").split(" ")[1]
        if (sessionRepo.findSessionModelByToken(jwtToken) == null) {
            throw Exception("")
        }

        val claims = jwtService.extractAllClaims(jwtToken)

        val user = userRepo.findUserByEmail(claims.subject) ?: throw Exception("No user with that credentials!")
        return ProfileResponseDTO(
            nickname = user.nickname,
            email = user.email,
            name = user.name,
            surname = user.surname,
            age = user.age
        )
    }

    fun logout(request: HttpServletRequest) : LogoutResponseDTO {
        val jwtToken = request.getHeader("Authorization").split(" ")[1]
        if (sessionRepo.findSessionModelByToken(jwtToken) == null) {
            throw Exception("")
        }

        val user = userRepo.findUserByEmail(jwtService.extractAllClaims(jwtToken).subject)
            ?: throw Exception("No user with that credentials!")

        val session = user.id?.let { sessionRepo.findSessionModelByUserId(it) }
        session?.id?.let { sessionRepo.deleteById(it) }
        return LogoutResponseDTO()
    }
}