package com.jluqgon214.Videogames.Library.repository

import com.jluqgon214.Videogames.Library.model.Library
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LibraryRepository : JpaRepository<Library, Long> {
    fun findByUserUserIdAndType(userId: Long, type: String): List<Library>
}