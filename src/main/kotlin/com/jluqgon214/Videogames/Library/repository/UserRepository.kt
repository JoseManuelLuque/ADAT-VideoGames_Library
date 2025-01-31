package com.jluqgon214.Videogames.Library.repository

import com.jluqgon214.Videogames.Library.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    // Implementar una derived query para obtener a un usuario por su nombre
    fun findByUsername(username: String): Optional<User>
}