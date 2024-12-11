package com.jluqgon214.Videogames.Library.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.net.URL
import java.util.Date

@Entity
@Table(name = "VideoGames")
data class Videogame(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var videogame_id: Long? = null,

    @Column(unique = true, nullable = false)
    var title: String? = null,

    @Column(nullable = false)
    var plataform: String? = null,

    @Column(nullable = false)
    var genre: String? = null,

    @Column(nullable = false)
    var release_date: Date? = null,

    var developer: String? = null,

    var cover: URL? = null

)