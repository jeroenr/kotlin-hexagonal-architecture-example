package com.github.jeroenr.hexagonal

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("component-test")
@SpringBootTest
class ApplicationTests : BaseComponentTest() {

    @Test
    fun contextLoads() {
    }
}
