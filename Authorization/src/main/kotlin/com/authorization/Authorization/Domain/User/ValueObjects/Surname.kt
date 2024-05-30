package com.authorization.Authorization.Domain.User.ValueObjects

import com.authorization.Authorization.Domain.Base.BaseValueObject
import com.authorization.Authorization.Domain.User.Validators.InitialsValidator

class Surname(rawSurname: String): BaseValueObject() {
    private var surname = InitialsValidator.validateInitials(rawSurname)
    init {
        atomicObjects.add(surname)
    }

    override fun toString(): String {
        return "Surname: $surname"
    }
}