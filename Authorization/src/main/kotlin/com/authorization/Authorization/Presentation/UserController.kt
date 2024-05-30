package com.authorization.Authorization.Presentation

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class UserController {
    @GetMapping("/users/")
    fun getUsers() {

    }

    @GetMapping("/users/{id}/")
    fun getUser(@PathVariable id: String) {

    }
}