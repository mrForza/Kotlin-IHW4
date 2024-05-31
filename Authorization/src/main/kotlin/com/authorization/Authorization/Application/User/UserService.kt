package com.authorization.Authorization.Application.User

import com.authorization.Authorization.Application.User.DTOs.UserDTO
import com.authorization.Authorization.Infrastructure.User.UserRepository
import com.authorization.Authorization.Infrastructure.User.UserRole
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findUserByNickName(username)

        return User(
            user?.email,
            user?.password,
            Collections.singleton(user?.role?.let { UserRole.valueOf(it) })
        )
    }

    fun loadUserCredentialsByEmail(email: String): UserDetails {
        val user = userRepository.findUserByEmail(email)

        return User(
            user?.email,
            user?.password,
            Collections.singleton(user?.role?.let { UserRole.valueOf(it) })
        )
    }

    fun getUsers(): MutableList<UserDTO> {
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

    fun getUserById(id: Int): UserDTO {
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