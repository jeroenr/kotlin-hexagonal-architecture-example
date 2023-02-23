package com.github.jeroenr.hexagonal.domain.model

import java.math.BigDecimal

/**
 * Sample DTO to communicate with a third party service
 */
data class ExchangeRateDto(val rate: BigDecimal, val currency: Currency)
