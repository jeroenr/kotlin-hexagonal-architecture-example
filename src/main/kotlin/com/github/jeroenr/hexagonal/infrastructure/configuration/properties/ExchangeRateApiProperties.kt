package com.github.jeroenr.hexagonal.infrastructure.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "exchange-rate.api")
data class ExchangeRateApiProperties(
    val endpoint: String,
    val clientId: String,
    val clientSecret: String
)
