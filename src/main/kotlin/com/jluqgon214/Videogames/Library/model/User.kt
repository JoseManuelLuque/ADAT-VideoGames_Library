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

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "userId")])
    @Column(name = "role", nullable = false)
    val roles: Set<Role>? = setOf(Role.USER)
)