package com.authorization.Authorization.Application.Authorization

import com.authorization.Authorization.Application.User.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(private val jwtService: JwtService, private val userService: UserService) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authHeader = request.getHeader("Authorization")

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response)
                return
            }

            val token = authHeader.substring(7)
            val claims = jwtService.extractAllClaims(token)

            if (claims.subject != null && SecurityContextHolder.getContext().authentication == null) {
                val user = userService.loadUserCredentialsByEmail(claims.subject)

                if (jwtService.isValid(token, user)) {
                    val authToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                    SecurityContextHolder.getContext().authentication = authToken
                }
            }

            filterChain.doFilter(request, response)
        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}