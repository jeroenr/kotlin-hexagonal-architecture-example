package com.github.jeroenr.hexagonal.domain.services

import com.github.jeroenr.hexagonal.domain.model.Currency
import com.github.jeroenr.hexagonal.domain.model.ExchangeRateDto
import com.github.jeroenr.hexagonal.domain.model.Money
import com.github.jeroenr.hexagonal.domain.model.UserAccountDto
import com.github.jeroenr.hexagonal.domain.ports.ExchangeRateApiClientPort
import com.github.jeroenr.hexagonal.domain.services.DepositService
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class DepositServiceTest {

    @Test
    fun `should make deposit according to conversion rate`(): Unit = runBlocking {
        val exchangeRateApiClient = mockk<ExchangeRateApiClientPort>()

        every {
            runBlocking {  exchangeRateApiClient.getRate(Currency.EUR, Currency.USD) }
        }.returns(ExchangeRateDto(BigDecimal.TEN, Currency.USD))

        val result = DepositService(exchangeRateApiClient).deposit(
            UserAccountDto(
                "123",
                "name",
                Money(BigDecimal.ZERO, Currency.USD)
            ),
            Money(BigDecimal.ONE, Currency.EUR)
        )
        assertThat(result).isEqualTo(UserAccountDto(
            "123",
            "name",
            Money(BigDecimal.TEN, Currency.USD)
        ))
    }
}
