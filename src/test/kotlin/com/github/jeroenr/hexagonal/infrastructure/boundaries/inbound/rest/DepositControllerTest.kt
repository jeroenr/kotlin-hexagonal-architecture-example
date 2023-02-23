package com.github.jeroenr.hexagonal.infrastructure.boundaries.inbound.rest

import com.github.jeroenr.hexagonal.domain.ports.ExchangeRateApiClientPort
import com.github.jeroenr.hexagonal.domain.ports.UserAccountEventPublisherPort
import com.github.jeroenr.hexagonal.domain.ports.UserAccountRepositoryPort
import io.mockk.clearAllMocks
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@ActiveProfiles("component-test")
@AutoConfigureWebTestClient
internal class DepositControllerTest(
        @Autowired val client: WebTestClient,
        @Autowired val exchangeRateApiClientPort: ExchangeRateApiClientPort,
        @Autowired val mongoTemplate: ReactiveMongoTemplate,
        @Autowired val userAccountRepository: UserAccountRepositoryPort,
        @Autowired val userAccountEventPublisherPort: UserAccountEventPublisherPort
) {
    /**
     * Preparing Mock Stub For service class
     **/
    @BeforeEach
    fun setup() {
        clearAllMocks()
        runBlocking {
            // clean db
            // populate db with mongoTemplate
        }
    }

    @Test
    fun `verify bad request when body is not provided`() {
        client.post().uri("/my/api/deposit").contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest
    }
}
