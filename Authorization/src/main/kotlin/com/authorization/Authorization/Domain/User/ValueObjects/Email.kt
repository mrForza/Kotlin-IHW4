package com.authorization.Authorization.Domain.User.ValueObjects

import com.authorization.Authorization.Domain.Base.BaseValueObject
import com.authorization.Authorization.Domain.User.Validators.EmailValidator

class Email(rawEmail: String) : BaseValueObject() {
    var email = EmailValidator.validateEmail(rawEmail)
    init {
        atomicObjects.add(email)
    }

    override fun toString(): String {
        return """
            Email: $email
                name: ${email.split("@")[0]}
                domain_1: ${email.split("@")[1].split(".")[0]}
                domain_1: ${email.split("@")[1].split(".")[1]}
        """.trimIndent()
    }
}