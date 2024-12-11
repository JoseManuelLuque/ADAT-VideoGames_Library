package com.jluqgon214.Videogames.Library.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import java.util.Date

@Entity
@Table(name = "Users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var user_id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String? = null,

    @Column(nullable = false)
    var password: String? = null,

    @Column(nullable = false)
    var register_date: Date? = null,

    var roles: String? = null

)