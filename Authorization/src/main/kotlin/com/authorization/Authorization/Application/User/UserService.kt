package com.authorization.Authorization.Application.User

import com.authorization.Authorization.Application.Authorization.Exceptions.BadSessionException
import com.authorization.Authorization.Application.Authorization.Exceptions.NoUserWithGivenCredentialsException
import com.authorization.Authorization.Application.User.DTOs.UserDTO
import com.authorization.Authorization.Infrastructure.Jwt.SessionRepository
import com.authorization.Authorization.Infrastructure.User.UserRepository
import com.authorization.Authorization.Infrastructure.User.UserRole
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findUserByNickName(username)
            ?: throw NoUserWithGivenCredentialsException("No user with that credentials!")

        return User(
            user.email,
            user.password,
            Collections.singleton(user.role.let { UserRole.valueOf(it) })
        )
    }

    fun loadUserCredentialsByEmail(email: String): UserDetails {
        val user = userRepository.findUserByEmail(email)
            ?: throw NoUserWithGivenCredentialsException("No user with that credentials!")

        return User(
            user.email,
            user.password,
            Collections.singleton(user.role.let { UserRole.valueOf(it) })
        )
    }

    fun getUsers(request: HttpServletRequest): MutableList<UserDTO> {
        val jwtToken = request.getHeader("Authorization").split(" ")[1]
        if (sessionRepository.findSessionModelByToken(jwtToken) == null) {
            throw BadSessionException("Your session has expired or you have transferred an invalid token")
        }

        val users =  userRepository.findAll().toMutableList()
        val userDtoS: MutableList<UserDTO> = mutableListOf()

        for (user in users) {
            userDtoS.add(UserDTO(
                id = user.id ?: 0,
                name = user.name,
                surname = user.surname,
                age = user.age
            ))
        }

        return userDtoS
    }

    fun getUserById(id: Int, request: HttpServletRequest): UserDTO {
        val jwtToken = request.getHeader("Authorization").split(" ")[1]
        if (sessionRepository.findSessionModelByToken(jwtToken) == null) {
            throw BadSessionException("Your session has expired or you have transferred an invalid token")
        }

        val optionalUser = userRepository.findById(id)
        if (optionalUser.isEmpty) {
            throw Exception("No user with this id!")
        }

        val user = optionalUser.get()
        return UserDTO(
            id = user.id ?: 0,
            name = user.name,
            surname = user.surname,
            age = user.age
        )
    }
}