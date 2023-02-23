package com.github.jeroenr.hexagonal.domain.ports

import com.github.jeroenr.hexagonal.domain.model.Currency
import com.github.jeroenr.hexagonal.domain.model.ExchangeRateDto

interface ExchangeRateApiClientPort {
    suspend fun getRate(source: Currency, target: Currency): ExchangeRateDto
}
