package com.jluqgon214.Videogames.Library.service

import com.jluqgon214.Videogames.Library.model.Library
import com.jluqgon214.Videogames.Library.repository.LibraryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LibraryService {

    @Autowired
    private lateinit var libraryRepository: LibraryRepository

    // Funcion para obtener todas las bibliotecas
    fun getAll(): List<Library> {
        return libraryRepository.findAll()
    }

    // Funcion para obtener una biblioteca por usuario y tipo
    fun getByUserIdAndType(userId: Long, type: String): List<Library> {
        return libraryRepository.findByUserUserIdAndType(userId, type)
    }

    // Funcion para crear una biblioteca
    @Transactional
    fun create(library: Library): Library {
        return libraryRepository.save(library)
    }

    // Funcion para borrar una biblioteca
    @Transactional
    fun delete(id: Long) {
        val library = libraryRepository.findById(id).orElseThrow {
            IllegalArgumentException("No library entry found with ID $id")
        }
        libraryRepository.delete(library)
    }
}