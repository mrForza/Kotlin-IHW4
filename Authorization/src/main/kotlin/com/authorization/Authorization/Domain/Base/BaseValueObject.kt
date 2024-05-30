package com.authorization.Authorization.Domain.Base

open class BaseValueObject {
    val atomicObjects: MutableList<Any> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is BaseValueObject) {
            return false;
        }

        for (i in atomicObjects.indices) {
            if (atomicObjects[i] != other.atomicObjects[i]) {
                return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        var hashCode = 101
        for (atomic in atomicObjects) {
            hashCode = hashCode * 37 + atomic.hashCode()
        }
        return hashCode
    }
}