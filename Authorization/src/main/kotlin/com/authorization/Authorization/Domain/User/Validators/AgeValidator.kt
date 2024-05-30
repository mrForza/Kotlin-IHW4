package com.authorization.Authorization.Domain.User.Validators

import com.authorization.Authorization.Domain.User.Exceptions.AgeException

class AgeValidator {
    companion object {
        fun validateAge(age: Int): Int {
            if (age <= 0) {
                throw AgeException("Your age should be greater than 0")
            } else if (age >= 130) {
                throw AgeException("Your age cannot be greater than 130")
            }

            return age
        }
    }
}