package com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.apiclient

import com.github.jeroenr.hexagonal.domain.model.Currency
import com.github.jeroenr.hexagonal.domain.model.ExchangeRateDto
import com.github.jeroenr.hexagonal.domain.ports.ExchangeRateApiClientPort
import com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.apiclient.model.ExchangeRateApiResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

/**
 * Sample third party API client. Internally uses third party API model, but implements our adapter and uses DTO for
 * communication with our domain layer
 */
@Component
class ExchangeRateApiClientAdapter(
    @Qualifier("exchangeRateApiClient")
    private val client: WebClient
) : ExchangeRateApiClientPort {
    override suspend fun getRate(source: Currency, target: Currency): ExchangeRateDto =
        getRate(source to target).let { (_, currency, factor) ->
            ExchangeRateDto(factor.toDouble().toBigDecimal(), Currency.valueOf(currency.toUpperCase()))
        }

    private suspend fun getRate(currencyPair: Pair<Currency, Currency>): ExchangeRateApiResponse =
        currencyPair.let { (from, to) ->
            client
                .get()
                .uri("rates/$from/$to")
                .retrieve()
                .awaitBody()
        }
}

