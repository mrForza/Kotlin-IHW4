package com.authorization.Authorization.Domain.User.Validators

import com.authorization.Authorization.Domain.User.Exceptions.PasswordException

class PasswordValidator {
    companion object {
        private const val GENERAL_ERROR_TEMPLATE = "Your password should have at least "

        private val exceptionHandler: (String, String, String) -> Unit = {
            text: String, regexString: String, exceptionMessage: String ->
            if (!text.contains(regexString.toRegex())) { throw PasswordException(exceptionMessage) }
        }

        private val controlData: List<Pair<String, String>> = listOf(
            Pair(
                "[a-zA-Z0-9~!@#\$%^&*()_+`\"№;:?=]{8,64}",
                GENERAL_ERROR_TEMPLATE + "8 symbols and no more than 64 symbols"
            ),
            Pair("[a-z]", GENERAL_ERROR_TEMPLATE + "1 lowercase letter"),
            Pair("[A-Z]", GENERAL_ERROR_TEMPLATE + "1 uppercase letter"),
            Pair("[0-9]", GENERAL_ERROR_TEMPLATE + "1 digit"),
            Pair("[~!@#\$%^&*()_+`\"№;:?=]", GENERAL_ERROR_TEMPLATE + "1 special symbol"),
        )

        fun validatePassword(password: String): String {
            for (item in controlData) {
                exceptionHandler(password, item.first, item.second)
            }
            return password
        }
    }
}