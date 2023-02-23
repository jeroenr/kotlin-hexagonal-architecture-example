package com.github.jeroenr.hexagonal.domain.model

/**
 * Sample DTO to communicate with our data layer
 */
data class UserAccountDto(
    val id: String,
    val name: String,
    val balance: Money
)
