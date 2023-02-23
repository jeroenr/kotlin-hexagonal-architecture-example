package com.github.jeroenr.hexagonal.infrastructure.configuration

import com.github.jeroenr.hexagonal.domain.ports.ExchangeRateApiClientPort
import com.github.jeroenr.hexagonal.domain.ports.UserAccountEventPublisherPort
import com.github.jeroenr.hexagonal.domain.ports.UserAccountRepositoryPort
import com.github.jeroenr.hexagonal.domain.services.DepositOrchestrationService
import com.github.jeroenr.hexagonal.domain.services.DepositService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ApplicationConfiguration {
    @Bean
    open fun depositService(exchangeRateApiClient: ExchangeRateApiClientPort): DepositService =
        DepositService(exchangeRateApiClient)

    @Bean
    open fun depositOrchestrationService(
            depositService: DepositService,
            userAccountRepositoryPort: UserAccountRepositoryPort,
            userAccountEventPublisherPort: UserAccountEventPublisherPort
    ): DepositOrchestrationService = DepositOrchestrationService(
        depositService, userAccountRepositoryPort, userAccountEventPublisherPort
    )
}
