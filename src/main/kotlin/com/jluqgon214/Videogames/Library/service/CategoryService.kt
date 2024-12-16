package com.jluqgon214.Videogames.Library.service

import com.jluqgon214.Videogames.Library.model.Category
import com.jluqgon214.Videogames.Library.model.Videogame
import com.jluqgon214.Videogames.Library.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService() {
    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    fun getAll(): List<Category> {
        return categoryRepository.findAll()
    }

    @Transactional
    fun create(category: Category): Category {
        return categoryRepository.save(category)
    }
}