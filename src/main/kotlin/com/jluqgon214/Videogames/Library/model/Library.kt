package com.jluqgon214.Videogames.Library.model

import jakarta.persistence.*

@Entity
@Table(name = "Libraries")
data class Library(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var library_id: Long? = null,

    @Column(nullable = false)
    var type: String, // Tipo de librería (por jugar, para compra, completados)

    // Relación muchos a uno con la tabla de usuarios
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, foreignKey = ForeignKey(name = "fk_libraries_user"))
    val user: User,

    // Relación muchos a uno con la tabla de videojuegos
    @ManyToOne(optional = false)
    @JoinColumn(name = "videogameId", referencedColumnName = "videogameId", nullable = false, foreignKey = ForeignKey(name = "fk_libraries_videogame"))
    val videogame: Videogame
)