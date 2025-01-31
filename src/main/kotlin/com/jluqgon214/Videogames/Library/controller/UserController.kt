package com.jluqgon214.Videogames.Library.controller

import com.jluqgon214.Videogames.Library.model.User
import com.jluqgon214.Videogames.Library.service.TokenService
import com.jluqgon214.Videogames.Library.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    /*
    MÉTODO PARA INSERTAR UN USUARIO
     */
    @PostMapping("/register")
    fun register(
        @RequestBody newUser: User
    ): ResponseEntity<Any> {
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
    }

    @PostMapping("/login")
    fun login(@RequestBody usuario: User): ResponseEntity<Any> {
        return try {
            val token = userService.login(usuario)
            ResponseEntity.ok(mapOf("token" to token, "mensaje" to "Inicio de sesión exitoso"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("mensaje" to "La contrasña no es correcta"))
        }
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }
}
