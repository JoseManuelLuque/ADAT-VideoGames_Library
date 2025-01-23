package com.jluqgon214.Videogames.Library.controller

import com.jluqgon214.Videogames.Library.model.Library
import com.jluqgon214.Videogames.Library.service.LibraryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/libraries")
class LibraryController {

    @Autowired
    private lateinit var libraryService: LibraryService

    @GetMapping
    fun getAll(): ResponseEntity<List<Library>> {
        return ResponseEntity(libraryService.getAll(), HttpStatus.OK)
    }

    @GetMapping("/user/{userId}/type/{type}")
    fun getByUserIdAndType(@PathVariable userId: Long, @PathVariable type: String): ResponseEntity<List<Library>> {
        return ResponseEntity(libraryService.getByUserIdAndType(userId, type), HttpStatus.OK)
    }

    @PostMapping
    fun create(@RequestBody library: Library): ResponseEntity<Library> {
        return ResponseEntity(libraryService.create(library), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        libraryService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}