package com.jluqgon214.Videogames.Library.service

import com.jluqgon214.Videogames.Library.model.Videogame
import com.jluqgon214.Videogames.Library.repository.VideogameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VideogameService() {
    @Autowired
    private lateinit var videogameRepository: VideogameRepository


    fun getAll(): List<Videogame> {
        return videogameRepository.findAll()
    }

    fun getById(id: Long): Videogame {
        return videogameRepository.findById(id).orElseThrow {
            IllegalArgumentException("No se encuentra ningun juego con la id: $id") // Lanzar excepción si no se encuentra
        }
    }

    @Transactional
    fun create(videogame: Videogame): Videogame {
        if (videogameRepository.existsByTitle(videogame.title!!)) {
            throw Exception("Ya existe un juego con este título") // Lanzar excepción si ya existe un juego con el mismo título
        }
        return videogameRepository.save(videogame)
    }

    @Transactional
    fun update(id: Long, videogame: Videogame): Videogame {
        val existing = getById(id)
        val updated = existing.copy(
            title = videogame.title,
            developer = videogame.developer,
            platform = videogame.platform,
            release_date = videogame.release_date,
            genre = videogame.genre
        )
        return videogameRepository.save(updated)
    }

    @Transactional
    fun delete(id: Long) {
        val videogame = getById(id)
        videogameRepository.delete(videogame)
    }
}
