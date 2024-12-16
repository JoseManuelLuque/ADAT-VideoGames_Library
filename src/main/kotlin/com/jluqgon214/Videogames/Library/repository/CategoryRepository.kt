package com.jluqgon214.Videogames.Library.repository

import com.jluqgon214.Videogames.Library.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByCategoryName(categoryName: String): List<Category>
}
