package com.jluqgon214.Videogames.Library.controller

import com.jluqgon214.Videogames.Library.model.Videogame
import com.jluqgon214.Videogames.Library.service.VideogameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/videogames")
class VideogameController(){

    @Autowired
    private lateinit var videogameService: VideogameService


    @GetMapping
    fun getAll(): ResponseEntity<List<Videogame>> {
        val videogames = videogameService.getAll()
        return ResponseEntity.ok(videogames)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Videogame> {
        val videogame = videogameService.getById(id)
        return ResponseEntity.ok(videogame)
    }

    @PostMapping
    fun create(@RequestBody videogame: Videogame): ResponseEntity<Videogame> {
        val newVideogame = videogameService.create(videogame)
        return ResponseEntity.status(HttpStatus.CREATED).body(newVideogame)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody videogame: Videogame): ResponseEntity<Videogame> {
        val updatedVideogame = videogameService.update(id, videogame)
        return ResponseEntity.ok(updatedVideogame)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        videogameService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
