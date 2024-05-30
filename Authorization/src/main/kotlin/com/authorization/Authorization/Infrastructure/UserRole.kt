package com.authorization.Authorization.Infrastructure

import org.springframework.security.core.GrantedAuthority

enum class UserRole : GrantedAuthority {
    ADMIN, USER;
    override fun getAuthority(): String {
        return name
    }
}