package com.github.jeroenr.hexagonal.domain.services

import com.github.jeroenr.hexagonal.domain.model.DepositRequestDto
import com.github.jeroenr.hexagonal.domain.model.DepositResponseDto
import com.github.jeroenr.hexagonal.domain.ports.UserAccountEventPublisherPort
import com.github.jeroenr.hexagonal.domain.ports.UserAccountRepositoryPort
import org.springframework.transaction.annotation.Transactional

/**
 * Example application service. Should be stateless. Orchestrates business operations. Addresses cross cutting
 * concerns such as transaction management, security, event publishing.
 */
open class DepositOrchestrationService(
        private val depositService: DepositService,
        private val userAccountRepositoryPort: UserAccountRepositoryPort,
        private val userAccountEventPublisherPort: UserAccountEventPublisherPort
) {
    @Transactional
    open suspend fun deposit(request: DepositRequestDto): DepositResponseDto? =
        userAccountRepositoryPort.findById(request.userId)?.let { userAccount ->
            val oldBalance = userAccount.balance
            val updated = depositService.deposit(userAccount, request.amount)
            userAccountRepositoryPort.save(updated)
            userAccountEventPublisherPort.publishUserAccountUpdated(
                userAccount.id,
                "Updated balance: $oldBalance -> ${updated.balance}"
            )
            DepositResponseDto(request.userId, oldBalance, updated.balance)
        }
}
