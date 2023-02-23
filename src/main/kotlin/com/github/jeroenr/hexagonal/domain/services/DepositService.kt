package com.github.jeroenr.hexagonal.domain.services

import com.github.jeroenr.hexagonal.domain.model.Currency
import com.github.jeroenr.hexagonal.domain.model.ExchangeRateDto
import com.github.jeroenr.hexagonal.domain.model.Money
import com.github.jeroenr.hexagonal.domain.model.UserAccountDto
import com.github.jeroenr.hexagonal.domain.ports.ExchangeRateApiClientPort
import java.math.BigDecimal

/**
 * Example domain service. Should be stateless, can contain business logic that
 * doesn't naturally fit in value objects.
 */
class DepositService(private val exchangeRateApiClient: ExchangeRateApiClientPort) {
    suspend fun deposit(userAccount: UserAccountDto, amount: Money): UserAccountDto {
        require(amount.largerThanZero) { "Amount must be larger than 0" }
        val rateToUsd = if (amount.currency != Currency.USD) {
            exchangeRateApiClient.getRate(amount.currency, Currency.USD)
        } else {
            ExchangeRateDto(BigDecimal.ONE, Currency.USD)
        }
        val rateToPref = if (userAccount.balance.currency != Currency.USD) {
            exchangeRateApiClient.getRate(Currency.USD, userAccount.balance.currency)
        } else {
            ExchangeRateDto(BigDecimal.ONE, Currency.USD)
        }
        return userAccount.copy(
            balance = userAccount.balance.add(
                amount
                    .convert(rateToUsd)
                    .convert(rateToPref)
            )
        )
    }
}
