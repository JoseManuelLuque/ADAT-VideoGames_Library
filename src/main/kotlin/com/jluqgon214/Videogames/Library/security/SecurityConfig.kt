// SecurityConfig.kt
package com.jluqgon214.Videogames.Library.security

import com.jluqgon214.Videogames.Library.service.UserService
import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {


    @Autowired
    private lateinit var RsaKeys: RSAKeysProperties

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    // Para logearte o registrarte no necesitas estar autentificado, ya que si no jamas podrias logearte o registrarte
                    .requestMatchers("/users/login", "/users/register").permitAll()

                    /*Usuarios*/

                    // Para mirar los usuarios guardados en la base de datos debes ser admin
                    .requestMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")

                    /*Librerias*/

                    // Para mirar las librerias globales debes ser admin
                    .requestMatchers(HttpMethod.GET, "/libraries").hasAuthority("ADMIN")
                    // Para mirar las librerias de un usuario debes estar autentificado
                    .requestMatchers(HttpMethod.GET, "/libraries/user/*").authenticated()
                    // Para crear o borrar una libreria debes estar autentificado
                    .requestMatchers(HttpMethod.POST, "/libraries").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/libraries/*").authenticated()

                    // Para añadir o eliminar un videojuego de una libreria debes estar autentificado
                    .requestMatchers(HttpMethod.DELETE, "/libraries/*/delete").authenticated()
                    .requestMatchers(HttpMethod.POST, "/libraries/*/add").authenticated()

                    /*Videojuegos*/

                    // Para mirar los videojuegos debes solo estar logeado da igual tu rol
                    .requestMatchers(HttpMethod.GET, "/videogames", "/videogames/*").authenticated()
                    // Pero para crear, actualizar o borrar videojuegos debes ser ADMIN
                    .requestMatchers(HttpMethod.POST, "/videogames").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/videogames/*").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/videogames/*").hasAuthority("ADMIN")


                    // Para el resto de consultas debes estar autentificado(ADMIN o USER)
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { oauth2 -> oauth2.jwt(Customizer.withDefaults()) }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .httpBasic(Customizer.withDefaults())
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk: JWK = RSAKey.Builder(RsaKeys.publicKey).privateKey(RsaKeys.privateKey).build()
        val jwks: JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(jwks)
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(RsaKeys.publicKey).build()
    }

}