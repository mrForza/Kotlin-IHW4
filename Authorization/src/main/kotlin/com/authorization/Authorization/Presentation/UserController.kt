package com.authorization.Authorization.Presentation

import com.authorization.Authorization.Application.User.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class UserController(private val userService: UserService) {
    @GetMapping("/users/")
    fun getUsers(request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(userService.getUsers(request), HttpStatusCode.valueOf(200))
        } catch (exception: Exception) {
            return ResponseEntity<Any>(HttpStatusCode.valueOf(401))
        }
    }

    @GetMapping("/users/{id}/")
    fun getUser(@PathVariable id: Int, request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity<Any>(userService.getUserById(id, request), HttpStatusCode.valueOf(200))
        } catch (exception: Exception) {
            return ResponseEntity<Any>(exception.message, HttpStatusCode.valueOf(400))
        }
    }
}