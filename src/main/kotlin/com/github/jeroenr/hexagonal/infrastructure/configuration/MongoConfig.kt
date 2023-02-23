package com.github.jeroenr.hexagonal.infrastructure.configuration

import com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb.converters.LocalDateTimeToStringWriteConverter
import com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb.converters.LocalDateToStringWriteConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb"])
open class MongoConfig {

    @Bean
    open fun mongoCustomConversions() = MongoCustomConversions(
        listOf(
            LocalDateToStringWriteConverter(),
            LocalDateTimeToStringWriteConverter()
        )
    )
}
