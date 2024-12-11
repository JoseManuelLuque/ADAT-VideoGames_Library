package com.jluqgon214.Videogames.Library.repository

import com.jluqgon214.Videogames.Library.model.Videogame
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideogameRepository : JpaRepository<Videogame, Long> {
    fun findByTitle(tittle: String): List<Videogame> // Ejemplo: buscar por título
}
