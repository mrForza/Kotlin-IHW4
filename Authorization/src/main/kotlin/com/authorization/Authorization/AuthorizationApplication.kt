package com.authorization.Authorization

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@ComponentScan
class AuthorizationApplication
fun main(args: Array<String>) {
	try {
		runApplication<AuthorizationApplication>(*args)
	} catch (exception: Exception) {
		println("Something went wrong!")
	}
}
