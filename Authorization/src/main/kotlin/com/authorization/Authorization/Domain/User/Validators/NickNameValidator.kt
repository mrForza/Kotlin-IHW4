package com.authorization.Authorization.Domain.User.Validators

import com.authorization.Authorization.Domain.User.Exceptions.NickNameException

class NickNameValidator {
    companion object {
        fun validateNickName(nickName: String): String {
            if (nickName.length < 8 || nickName.length > 64) {
                throw NickNameException("Your nickname should have at least 8 symbols and no more than 64 symbols")
            }
            return nickName
        }
    }
}