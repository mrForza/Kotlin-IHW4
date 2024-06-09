package com.authorization.Authorization.Application.Authorization.Exceptions

import com.authorization.Authorization.Application.Authorization.Exceptions.Base.BaseAuthorizationException

class NoUserWithGivenCredentialsException(message: String): BaseAuthorizationException(message) {

}