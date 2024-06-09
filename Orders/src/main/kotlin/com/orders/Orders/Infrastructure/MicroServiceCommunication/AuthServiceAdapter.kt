package com.orders.Orders.Infrastructure.MicroServiceCommunication

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class AuthServiceAdapter {
    private val baseAuthServiceURL = "http://authorization:8080/"

    fun checkUserAuthorization(request: HttpServletRequest): Any {
        val url = baseAuthServiceURL + "check/"
        val client = HttpClient.newBuilder().build()
        val req = HttpRequest.newBuilder()
            .uri(URI.create(url)).header(
                "Authorization",
                request.getHeader("Authorization")
            ).build()

        val response = client.send(req, HttpResponse.BodyHandlers.ofString())
        return response.body().toString()
    }
}