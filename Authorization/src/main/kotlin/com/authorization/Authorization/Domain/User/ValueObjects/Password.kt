package com.authorization.Authorization.Domain.User.ValueObjects

import com.authorization.Authorization.Domain.Base.BaseValueObject
import com.authorization.Authorization.Domain.User.Validators.PasswordValidator
import java.math.BigInteger
import java.security.MessageDigest

class Password(rawPassword: String) : BaseValueObject() {
    private var hashedPassword: String = makeHash(PasswordValidator.validatePassword(rawPassword))
    init {
        atomicObjects.add(hashedPassword)
    }

    private fun makeHash(rawPassword: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(
            1,
            md.digest(rawPassword.toByteArray())
        ).toString(16).padStart(32, '0')
    }

    override fun toString(): String {
        return "Password: $hashedPassword"
    }
}