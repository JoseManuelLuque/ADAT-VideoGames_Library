package com.jluqgon214.Videogames.Library.model

import jakarta.persistence.*

@Entity
@Table(name = "Categories")
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var category_id: Long? = null,

    var categoryName: String? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL])
    val videogames: List<Videogame> = listOf()
)