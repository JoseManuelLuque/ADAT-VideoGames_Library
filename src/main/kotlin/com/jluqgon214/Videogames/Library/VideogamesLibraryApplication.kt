package com.jluqgon214.Videogames.Library

import com.jluqgon214.Videogames.Library.security.RSAKeysProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RSAKeysProperties::class)
class VideogamesLibraryApplication

fun main(args: Array<String>) {
	runApplication<VideogamesLibraryApplication>(*args)
}
