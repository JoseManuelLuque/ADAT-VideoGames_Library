package com.jluqgon214.Videogames.Library.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.Date
import kotlin.collections.joinToString
import kotlin.collections.map

@Service
class TokenService {
    @Autowired
    private lateinit var jwtEncoder: JwtEncoder

    fun generateToken(authentication: Authentication): String {

        println(authentication)
        val roles: String = authentication.authorities.joinToString(" ") { it.authority } // Contiene los roles del usuario


        val payload: JwtClaimsSet = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Date().toInstant().plus(Duration.ofHours(1)))
            .subject(authentication.name)
            .claim("roles", roles)
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(payload)).tokenValue
    }
}