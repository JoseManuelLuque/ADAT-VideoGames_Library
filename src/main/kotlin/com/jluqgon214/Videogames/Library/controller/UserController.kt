package com.jluqgon214.Videogames.Library.controller

import com.jluqgon214.Videogames.Library.model.User
import com.jluqgon214.Videogames.Library.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.function.Consumer

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var jwtEncoder: JwtEncoder

    /*
    MÉTODO PARA INSERTAR UN USUARIO
     */
    @PostMapping("/register")
    fun register(
        @RequestBody newUser: User
    ): ResponseEntity<Any> {
        try {
            // Validación mínima: Verificar que el nombre de usuario y la contraseña no estén vacíos
            if (newUser.username.isNullOrBlank() || newUser.password.isNullOrBlank()) {
                return ResponseEntity.badRequest()
                    .body(mapOf("mensaje" to "El nombre de usuario y la contraseña son obligatorios."))
            }

            // Validación adicional: Verificar longitud mínima de la contraseña
            if (newUser.password.toString().length < 6) {
                return ResponseEntity.badRequest()
                    .body(mapOf("mensaje" to "La contraseña debe tener al menos 6 caracteres."))
            }

            // Registrar el nuevo usuario a través del UserService
            val savedUser = userService.registerUsuario(newUser)

            // Retornar una respuesta exitosa con el usuario registrado
            return ResponseEntity(savedUser, HttpStatus.CREATED)

        } catch (e: RuntimeException) {
            // Manejo de errores, como si el usuario ya existe
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(mapOf("mensaje" to e.message))
        } catch (e: Exception) {
            // Manejo de cualquier otro error no esperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("mensaje" to "Ocurrió un error al registrar el usuario. Inténtelo de nuevo."))
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody usuario: User): ResponseEntity<Any> {
        return try {
            val token = userService.login(usuario)
            ResponseEntity.ok(mapOf("token" to token, "mensaje" to "Inicio de sesión exitoso"))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("mensaje" to e.message))
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapOf("mensaje" to e.message))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("mensaje" to "Ocurrió un error interno"))
        }
    }

    // Método auxiliar para crear el JWT
    fun generateJwtToken(claims: Map<String, Any>): String {
        val now = Instant.now()
        val jwtClaimsSet = JwtClaimsSet.builder()
            .claims(claims as Consumer<Map<String?, Any?>?>?)
            .subject(claims["username"].toString())
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS)) // Expiración en 1 hora
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).tokenValue
    }
}
