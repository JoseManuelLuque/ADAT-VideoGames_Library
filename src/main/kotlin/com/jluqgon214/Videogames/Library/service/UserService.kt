package com.jluqgon214.Videogames.Library.service

import com.jluqgon214.Videogames.Library.model.User
import com.jluqgon214.Videogames.Library.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*


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

    fun login(userLogin: User): String? {
        // Validación de los campos de entrada
        if (userLogin.password.isNullOrBlank() || userLogin.username.isNullOrBlank()) {
            throw IllegalArgumentException("El nombre de usuario y la contraseña son obligatorios")
        }


        // COMPROBAR CREDENCIALES
        // Buscar el usuario en la base de datos
        val userBD: User = userReposiroty.findByUsername(userLogin.username!!)
            .orElseThrow { NoSuchElementException("El usuario proporcionado no existe en la base de datos") }

        // 2 Compruebo nombre y pass
        if (passwordEncoder.matches(userLogin.password, userBD.password)) {
            // 3 GENERAR EL TOKEN
            var token: String = ""
            token = UUID.randomUUID().toString()

            /*// 4 CREAR UNA SESSION
            val s: Session = Session(
                null,
                token,
                userBD,
                LocalDateTime.now().plusMinutes(3)
            )

            // 5 INSERTAMOS EN BDD
            sessionRepository.save(s)*/

            return token
        } else {
            // SI LA CONTRASEÑA NO COINCIDE, LANZAMOS EXCEPCIÓN
            throw NotFoundException(/*"Las credenciales son incorrectas"*/)
        }
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
            password = passwordEncoder.encode(usuario.password),
            roles = usuario.roles,
            register_date = Timestamp(Date().time)
        )

        // Guardamos el newUsuario en la base de datos... igual que siempre
        userReposiroty.save(newUser)

        // Devolvemos el Usuario insertado en la BDD
        return newUser

    }

}