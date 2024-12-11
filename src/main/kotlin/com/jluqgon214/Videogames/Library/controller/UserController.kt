package com.jluqgon214.Videogames.Library.controller

import com.jluqgon214.Videogames.Library.model.User
import com.jluqgon214.Videogames.Library.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    /*
    MÉTODO PARA INSERTAR UN USUARIO
     */
    @PostMapping("/register")
    fun register(
        @RequestBody newUser: User
    ): ResponseEntity<User?>? {

        // Comprobación mínima
        // -> La obviamos por ahora

        // Llamar al UsuarioService para insertar un usuario


        // Devolver el usuario insertado
        return ResponseEntity(null, HttpStatus.CREATED) // Cambiar null por el usuario insertado

    }

    @PostMapping("/login")

    fun login(@RequestBody usuario: User): ResponseEntity<Any>? {
        val authentication: Authentication

        try {
            authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    usuario.username,
                    usuario.password
                )
            )
        } catch (e: AuthenticationException) {
            return ResponseEntity(mapOf("mensaje" to "Credenciales incorrectas"), HttpStatus.UNAUTHORIZED)
        }


        println(authentication)
        return null
    }
}
