package com.jluqgon214.Videogames.Library.service

import com.jluqgon214.Videogames.Library.model.User
import com.jluqgon214.Videogames.Library.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {

    @Autowired
    private lateinit var userReposiroty: UserRepository


    /*
    TODO
     */
    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User = userReposiroty
            .findByUsername(username!!)
            .orElseThrow()

        return User
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


        // Creamos la instancia de Usuario


        /*
         La password del newUsuario debe estar hasheada, así que usamos el passwordEncoder que tenemos definido.
         ¿De dónde viene ese passwordEncoder?
         El objeto passwordEncoder está definido al principio de esta clase.
         */


        // Guardamos el newUsuario en la base de datos... igual que siempre


        // Devolvemos el Usuario insertado en la BDD
        return null // Cambiar null por el usuario

    }
}