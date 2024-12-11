package com.jluqgon214.Videogames.Library.service

import com.jluqgon214.Videogames.Library.model.User
import com.jluqgon214.Videogames.Library.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userReposiroty: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User = userReposiroty
            .findByUsername(username!!)
            .orElseThrow()

        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.username)
            .password(user.password)
            .roles(user.roles)
            .build()
    }

    /*
    MÉTODO PARA INSERTAR UN USUARIO
     */
    fun registerUsuario(usuario: User): User? {

        // Comprobamos que el usuario no existe en la base de datos
        userReposiroty.findByUsername(usuario.username!!).ifPresent {
            throw RuntimeException("El usuario ya existe")
        }

        // Creamos la instancia de Usuario
        val newUser = User(
            username = usuario.username,
            password = usuario.password,
            roles = usuario.roles
        )

        /*
         La password del newUsuario debe estar hasheada, así que usamos el passwordEncoder que tenemos definido.
         ¿De dónde viene ese passwordEncoder?
         El objeto passwordEncoder está definido al principio de esta clase.
         */
        passwordEncoder.encode(newUser.password)

        // Guardamos el newUsuario en la base de datos... igual que siempre
        userReposiroty.save(newUser)

        // Devolvemos el Usuario insertado en la BDD
        return usuario

    }
}