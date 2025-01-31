package com.jluqgon214.Videogames.Library.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.persistence.ManyToOne
import java.util.Date

@Suppress("JpaObjectClassSignatureInspection")
@Entity
@Table(name = "Videogames")
data class Videogame(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var videogameId: Long? = null,

    @Column(unique = true, nullable = false)
    var title: String? = null,

    @Column(nullable = false)
    var platform: String? = null,

    @Column(nullable = false)
    var genre: String? = null,

    @Column
    var release_date: Date? = null,

    @Column
    var developer: String? = null
)

data class VideogameIdsRequest(
    val videogameIds: List<Long>
)