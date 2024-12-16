package com.jluqgon214.Videogames.Library.controller

import com.jluqgon214.Videogames.Library.model.Category
import com.jluqgon214.Videogames.Library.model.User
import com.jluqgon214.Videogames.Library.model.Videogame
import com.jluqgon214.Videogames.Library.service.CategoryService
import com.jluqgon214.Videogames.Library.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.printStackTrace

@RestController
@RequestMapping("/categories")
class CategoryController {

    @Autowired
    private lateinit var categoryService: CategoryService

    @GetMapping
    fun getCategories(): ResponseEntity<List<Category>> {
        val categories: List<Category> = categoryService.getAll()
        return ResponseEntity.ok(categories)
    }

    @PostMapping("/add")
    fun create(@RequestBody category: Category): ResponseEntity<Category> {
        try {
            val newCategory = categoryService.create(category)
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory)
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }
}