package com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb

import com.github.jeroenr.hexagonal.domain.ports.UserAccountRepositoryPort
import com.github.jeroenr.hexagonal.domain.model.UserAccountDto
import com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb.entities.UserAccountEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

/**
 * Adapter to convert our entity to our DTO and back,
 */
@Component
class UserAccountRepositorySpringDataAdapter(private val userAccountRepository: UserAccountRepository) :
        UserAccountRepositoryPort {
    override suspend fun findById(id: String): UserAccountDto? =
        userAccountRepository.findById(id)?.toUserAccountDto()

    override suspend fun save(userAccount: UserAccountDto): UserAccountDto =
        userAccountRepository.save(UserAccountEntity.of(userAccount)).toUserAccountDto()
}

/**
 * Sample of an actual spring data repository. In this case it uses coroutines.
 */
@Repository
interface UserAccountRepository : CoroutineCrudRepository<UserAccountEntity, String>
