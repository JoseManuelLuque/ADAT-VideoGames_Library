package com.jluqgon214.Videogames.Library.model

import jakarta.persistence.*

@Suppress("JpaObjectClassSignatureInspection")
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


    @ManyToMany
    @JoinTable(
        name = "Library_Videogame",
        joinColumns = [JoinColumn(name = "library_id")],
        inverseJoinColumns = [JoinColumn(name = "videogameId")]
    )
    val videogames: MutableList<Videogame>? = mutableListOf()
)

data class LibraryRequest(
    val type: String,
    val userId: Long,
    val videogameIds: List<Long>
)