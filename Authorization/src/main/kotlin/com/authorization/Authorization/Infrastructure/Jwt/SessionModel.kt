package com.authorization.Authorization.Infrastructure.Jwt

import com.authorization.Authorization.Infrastructure.User.UserModel
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "session")
class SessionModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserModel,
    @Column(length = 128) val token: String,
    val expires: Timestamp
) {

}