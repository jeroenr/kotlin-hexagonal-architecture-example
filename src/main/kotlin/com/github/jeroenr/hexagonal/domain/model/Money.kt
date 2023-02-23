package com.github.jeroenr.hexagonal.domain.model

import java.math.BigDecimal

enum class Currency { USD, EUR }

/**
 * Sample value object
 */
data class Money(
        val amount: BigDecimal,
        val currency: Currency,
) {
    val largerThanZero = amount > BigDecimal.ZERO
    fun add(o: Money): Money {
        if(currency != o.currency) throw IllegalArgumentException()
        return Money(amount.add(o.amount), currency)
    }
    fun convert(exchangeRate: ExchangeRateDto): Money {
        return Money(amount.multiply(exchangeRate.rate), exchangeRate.currency)
    }
}


