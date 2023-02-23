package com.github.jeroenr.hexagonal

import com.github.jeroenr.hexagonal.infrastructure.configuration.JacksonConfiguration

open class BaseComponentTest {

    companion object {
        fun readTestFile(path: String): String {
            return object {}.javaClass.getResource(path)?.readText(Charsets.UTF_8)!!
        }

        inline fun <reified T> parseTestFile(path: String): T =
            mapper.readValue(readTestFile(path), T::class.java)

        val mapper = JacksonConfiguration().objectMapper()
    }
}
