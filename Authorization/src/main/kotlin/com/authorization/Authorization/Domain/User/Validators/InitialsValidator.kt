package com.authorization.Authorization.Domain.User.Validators

import com.authorization.Authorization.Domain.User.Exceptions.NickNameException

class InitialsValidator {
    companion object {
        fun validateInitials(initials: String): String {
            if (initials.length > 32) {
                throw NickNameException("Your initials should have no more than 32 symbols")
            }
            return initials
        }
    }
}