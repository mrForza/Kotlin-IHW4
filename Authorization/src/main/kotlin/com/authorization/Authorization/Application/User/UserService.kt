package com.authorization.Authorization.Application.User

import com.authorization.Authorization.Infrastructure.UserRepository
import com.authorization.Authorization.Infrastructure.UserRole
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
            user?.getEmail(),
            user?.getPassword(),
            Collections.singleton(user?.role?.let { UserRole.valueOf(it) })
        )
    }

    fun loadUserCredentialsByEmail(email: String): UserDetails {
        val user = userRepository.findUserByEmail(email)

        return User(
            user?.getEmail(),
            user?.getPassword(),
            Collections.singleton(user?.role?.let { UserRole.valueOf(it) })
        )
    }
}