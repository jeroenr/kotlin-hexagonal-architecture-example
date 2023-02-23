package com.github.jeroenr.hexagonal.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class MoneyTest {

    @Test
    fun `should sum money with same currency`() {
        val sum = Money(BigDecimal.ONE, Currency.EUR).add(Money(BigDecimal.TEN, Currency.EUR))
        assertThat(sum).isEqualTo(Money(BigDecimal.valueOf(11), Currency.EUR))
    }

    @Test
    fun `should not allow sum money with different currency`() {
        assertThrows(IllegalArgumentException::class.java) {
            Money(BigDecimal.ONE, Currency.EUR).add(Money(BigDecimal.TEN, Currency.USD))
        }
    }

    @Test
    fun `should convert money according to exchange rate`() {
        val converted = Money(BigDecimal.TEN, Currency.EUR)
            .convert(ExchangeRateDto(BigDecimal.TEN, Currency.USD))
        assertThat(converted).isEqualTo(Money(BigDecimal.valueOf(100), Currency.USD))
    }
}
