package com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.apiclient.model

/**
 * Sample of a third party API model
 */
data class ExchangeRateApiResponse(
    val baseCurrency: String,
    val currency: String,
    val factor: Number
)