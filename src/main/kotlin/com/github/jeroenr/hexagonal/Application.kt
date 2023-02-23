package com.github.jeroenr.hexagonal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
