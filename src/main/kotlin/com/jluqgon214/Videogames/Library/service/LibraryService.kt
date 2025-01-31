package com.jluqgon214.Videogames.Library.service

import com.jluqgon214.Videogames.Library.model.Library
import com.jluqgon214.Videogames.Library.model.LibraryRequest
import com.jluqgon214.Videogames.Library.model.User
import com.jluqgon214.Videogames.Library.model.Videogame
import com.jluqgon214.Videogames.Library.repository.LibraryRepository
import com.jluqgon214.Videogames.Library.repository.UserRepository
import com.jluqgon214.Videogames.Library.repository.VideogameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LibraryService {

    @Autowired
    private lateinit var libraryRepository: LibraryRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var videogameRepository: VideogameRepository

    fun getAll(): List<Library> {
        return libraryRepository.findAll()
    }

    fun getByUserIdAndType(userId: Long, type: String): List<Library> {
        return libraryRepository.findByUserUserIdAndType(userId, type)
    }

    @Transactional
    fun getByUserId(userId: Long): List<Library> {
        return libraryRepository.findByUserUserId(userId)
    }

    @Transactional
    fun create(request: LibraryRequest): Library {
        val user: User = userRepository.findById(request.userId).orElseThrow()
        val videogames: List<Videogame> = videogameRepository.findAllById(request.videogameIds)
        val library = Library(
            type = request.type,
            user = user,
            videogames = videogames.toMutableList()
        )
        return libraryRepository.save(library)
    }

    @Transactional
    fun delete(id: Long) {
        val library = libraryRepository.findById(id).orElseThrow {
            IllegalArgumentException("No library entry found with ID $id")
        }
        libraryRepository.delete(library)
    }

    @Transactional
    fun addVideogamesToLibrary(libraryId: Long, videogameIds: List<Long>) {
        val library: Library = libraryRepository.findById(libraryId).orElseThrow()
        val videogames: List<Videogame> = videogameRepository.findAllById(videogameIds)
        library.videogames!!.addAll(videogames)
        libraryRepository.save(library)
    }

    @Transactional
    fun removeVideogamesFromLibrary(libraryId: Long, videogameIds: List<Long>) {
        val library: Library = libraryRepository.findById(libraryId).orElseThrow()
        val videogames: List<Videogame> = videogameRepository.findAllById(videogameIds)
        library.videogames!!.removeAll(videogames)
        libraryRepository.save(library)
    }
}