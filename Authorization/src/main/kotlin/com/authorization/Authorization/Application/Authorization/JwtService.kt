package com.authorization.Authorization.Application.Authorization

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import com.authorization.Authorization.Infrastructure.User.UserModel
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {
    private val secretKey = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4effa012586f3c8a9d2b5f8e3a9c8b5f6v8a3d9"

    private fun signingKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts
            .parser().verifyWith(signingKey())
            .build().parseSignedClaims(token).payload
    }

    fun isValid(token: String, user: UserDetails): Boolean {
        return extractAllClaims(token).subject == user.username &&
                !extractAllClaims(token).expiration.before(Date())
    }

    fun generateToken(user: UserModel): String {
        return Jwts
            .builder().subject(user.email)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(signingKey())
            .compact()
    }
}