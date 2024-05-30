package com.authorization.Authorization.Infrastructure

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import org.springframework.security.core.GrantedAuthority
import java.util.*

@Entity
@Table(name = "user")
class UserModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private val id: Int?,
    @Column(unique = true) private val nickname: String,
    @Column(unique = true) private val email: String,
    private val password: String,
    private val name: String,
    private val surname: String,
    @NotNull private val age: Int,
    @NotNull private val created: Date,
    @NotNull var role: String = UserRole.USER.toString()
) : GrantedAuthority{
    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }

    fun getNickName(): String {
        return nickname
    }

    override fun getAuthority(): String {
        return role
    }
}