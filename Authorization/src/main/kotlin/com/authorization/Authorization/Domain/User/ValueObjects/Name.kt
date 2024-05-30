package com.authorization.Authorization.Domain.User.ValueObjects

import com.authorization.Authorization.Domain.Base.BaseValueObject
import com.authorization.Authorization.Domain.User.Validators.InitialsValidator

class Name(rawName: String) : BaseValueObject() {
    private var name = InitialsValidator.validateInitials(rawName)
    init {
        atomicObjects.add(name)
    }

    override fun toString(): String {
        return "Name: $name"
    }
}