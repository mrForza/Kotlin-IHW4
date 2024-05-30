package com.authorization.Authorization.Domain.User.ValueObjects

import com.authorization.Authorization.Domain.Base.BaseValueObject
import com.authorization.Authorization.Domain.User.Validators.AgeValidator

class Age(rawAge: Int) : BaseValueObject() {
    private var age = AgeValidator.validateAge(rawAge)
    init {
        atomicObjects.add(age)
    }

    override fun toString(): String {
        return "Age: $age"
    }
}