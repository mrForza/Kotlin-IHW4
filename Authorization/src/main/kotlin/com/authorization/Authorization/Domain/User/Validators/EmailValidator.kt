package com.authorization.Authorization.Domain.User.Validators

import com.authorization.Authorization.Domain.User.Exceptions.EmailException
import com.authorization.Authorization.Domain.User.Exceptions.NickNameException

class EmailValidator {
    companion object {
        fun validateEmail(email: String): String {
            if (email.length > 64) {
                throw NickNameException("Your email should have no more than 64 symbols")
            }

            val separatedParts = email.split("@")
            if (separatedParts.size > 2) {
                throw NickNameException("Incorrect email structure. The email address should consist of only 1 @")
            }

            val emailInfo: Pair<String, String> = Pair(separatedParts[0], separatedParts[1])
            if (emailInfo.second.split(".").size > 2) {
                throw EmailException("Incorrect email domain. The email address should consist of only 1 @")
            }

            return email
        }
    }
}