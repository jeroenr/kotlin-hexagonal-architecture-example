package com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb

import com.github.jeroenr.hexagonal.config.TestMongoConfig
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
@Import(TestMongoConfig::class)
@ActiveProfiles("component-test")
internal class UserAccountRepositoryTest(
    @Autowired private val userAccountRepository: UserAccountRepository
) {
    @Test
    fun `should get all ready to pick up offers`(): Unit = runBlocking {
        // given

        // when
        val result = userAccountRepository.findById("DOES_NOT_EXIST")

        // then
        assertThat(result).isNull()
    }
}
