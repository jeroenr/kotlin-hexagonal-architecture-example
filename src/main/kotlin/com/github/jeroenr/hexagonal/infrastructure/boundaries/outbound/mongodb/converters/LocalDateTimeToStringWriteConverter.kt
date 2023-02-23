package com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@WritingConverter
class LocalDateTimeToStringWriteConverter : Converter<LocalDateTime, String> {
    override fun convert(date: LocalDateTime): String =
        date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}
