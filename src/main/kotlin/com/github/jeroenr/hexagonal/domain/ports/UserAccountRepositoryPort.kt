package com.github.jeroenr.hexagonal.domain.ports

import com.github.jeroenr.hexagonal.domain.model.UserAccountDto

interface UserAccountRepositoryPort {
    suspend fun findById(id: String): UserAccountDto?
    suspend fun save(userAccount: UserAccountDto): UserAccountDto
}
