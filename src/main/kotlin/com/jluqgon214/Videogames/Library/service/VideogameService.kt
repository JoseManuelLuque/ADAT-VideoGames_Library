package com.jluqgon214.Videogames.Library.service

import com.jluqgon214.Videogames.Library.model.Videogame
import com.jluqgon214.Videogames.Library.repository.VideogameRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VideogameService(
    private val videogameRepository: VideogameRepository
) {

    fun getAll(): List<Videogame> {
        return videogameRepository.findAll()
    }

    fun getById(id: Long): Videogame {
        return videogameRepository.findById(id).orElseThrow {
            IllegalArgumentException("No videogame found with ID $id") // Lanzar excepción si no se encuentra
        }
    }

    @Transactional
    fun create(videogame: Videogame): Videogame {
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
