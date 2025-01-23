package com.jluqgon214.Videogames.Library.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "Users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String? = null,

    @Column(nullable = false)
    var password: String? = null,

    @Column(nullable = false)
    var register_date: Date? = null,

    var roles: Role? = Role.USER
)