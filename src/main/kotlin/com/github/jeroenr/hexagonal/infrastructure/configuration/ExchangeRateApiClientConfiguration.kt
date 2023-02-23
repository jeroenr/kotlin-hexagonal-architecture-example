package com.github.jeroenr.hexagonal.infrastructure.configuration

import com.github.jeroenr.hexagonal.infrastructure.configuration.properties.ExchangeRateApiProperties
import io.netty.handler.logging.LogLevel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat

/**
 * Sample configuration of imaginary exchange rate API
 */
@Configuration
@EnableConfigurationProperties(ExchangeRateApiProperties::class)
open class ExchangeRateApiClientConfiguration(
    private val exchangeRateApiProperties: ExchangeRateApiProperties
) {
    @Bean
    @Qualifier("exchangeRateApiClient")
    open fun exchangeRateApiClient(builder: WebClient.Builder): WebClient =
        builder
            .baseUrl(exchangeRateApiProperties.endpoint)
            .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .defaultHeader("client-id", exchangeRateApiProperties.clientId)
            .defaultHeader("client-secret", exchangeRateApiProperties.clientSecret)
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create().wiretap(
                        "reactor.netty.http.client.HttpClient",
                        LogLevel.DEBUG,
                        AdvancedByteBufFormat.TEXTUAL
                    )
                )
            )
            .build()
}


