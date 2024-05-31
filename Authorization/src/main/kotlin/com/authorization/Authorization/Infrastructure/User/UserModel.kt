package com.authorization.Authorization.Infrastructure.User

import com.authorization.Authorization.Domain.User.UserEntity
import com.authorization.Authorization.Domain.User.Validators.PasswordValidator
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@Entity
@Table(name = "user")
class UserModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(unique = true, length = 64) val nickname: String,
    @Column(unique = true, length = 64) val email: String,
    @Column(length = 64) var password: String,
    @Column(length = 32) val name: String,
    @Column(length = 32) val surname: String,
    @NotNull val age: Int,
    @NotNull val created: Date,
    @NotNull var role: String = UserRole.USER.toString()
) : GrantedAuthority{

    companion object {
        fun hashPassword(password: String): String {
            return BCryptPasswordEncoder().encode(PasswordValidator.validatePassword(password))
        }

        fun convertModelEntityToDomainEntity(userModel: UserModel): UserEntity {
            return UserEntity(
                id = userModel.id,
                rawNickName = userModel.nickname,
                rawEmail = userModel.email,
                rawPassword = userModel.password,
                rawName = userModel.name,
                rawSurname = userModel.surname,
                rawAge = userModel.age,
                createdAt = userModel.created
            )
        }
    }

    override fun getAuthority(): String {
        return role
    }
}