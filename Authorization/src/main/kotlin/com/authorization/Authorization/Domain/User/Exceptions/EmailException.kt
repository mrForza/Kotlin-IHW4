package com.authorization.Authorization.Domain.User.Exceptions

import com.authorization.Authorization.Domain.User.Exceptions.Base.BaseUserException

class EmailException(message: String) : BaseUserException(message) {
}