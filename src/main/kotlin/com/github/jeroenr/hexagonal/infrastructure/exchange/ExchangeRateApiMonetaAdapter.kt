package com.github.jeroenr.hexagonal.infrastructure.exchange

import com.github.jeroenr.hexagonal.domain.model.Currency
import com.github.jeroenr.hexagonal.domain.model.ExchangeRateDto
import com.github.jeroenr.hexagonal.domain.ports.ExchangeRateApiClientPort
import org.springframework.stereotype.Component
import java.math.BigDecimal
import javax.money.convert.MonetaryConversions

/**
 * Sample third party API client. Internally uses third party API model, but implements our adapter and uses DTO for
 * communication with our domain layer
 */
@Component
class ExchangeRateApiMonetaAdapter : ExchangeRateApiClientPort {
    override suspend fun getRate(source: Currency, target: Currency): ExchangeRateDto {
        val rate = MonetaryConversions
            .getConversion(source.toString())
            .getExchangeRate(org.javamoney.moneta.Money.of(1, target.toString()))
        return ExchangeRateDto(
            rate.factor.numberValueExact(BigDecimal::class.java),
            Currency.valueOf(rate.currency.currencyCode)
        )
    }
}

