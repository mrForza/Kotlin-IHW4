package com.authorization.Authorization.Domain.Base

open class BaseEntity(val id: Int?) {
    override operator fun equals(other: Any?): Boolean {
        if (other == null || other !is BaseEntity) {
            return false
        }

        return id == other.id;
    }

    override fun hashCode(): Int {
        if (id == null) {
            return (Int.MIN_VALUE..Int.MAX_VALUE).random()
        }
        return id
    }
}