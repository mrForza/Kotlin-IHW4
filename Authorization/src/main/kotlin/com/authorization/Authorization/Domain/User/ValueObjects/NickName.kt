package com.authorization.Authorization.Domain.User.ValueObjects

import com.authorization.Authorization.Domain.Base.BaseValueObject
import com.authorization.Authorization.Domain.User.Validators.NickNameValidator

class NickName(rawNickName: String) : BaseValueObject() {
    private val nickName = NickNameValidator.validateNickName(rawNickName)
    init {
        atomicObjects.add(nickName)
    }
    override fun toString(): String {
        return "NickName: $nickName"
    }
}