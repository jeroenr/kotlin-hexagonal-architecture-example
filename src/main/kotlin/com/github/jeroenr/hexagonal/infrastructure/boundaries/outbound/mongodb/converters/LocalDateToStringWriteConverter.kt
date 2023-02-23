package com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@WritingConverter
class LocalDateToStringWriteConverter : Converter<LocalDate, String> {
    override fun convert(date: LocalDate): String =
        date.format(DateTimeFormatter.ISO_LOCAL_DATE)
}
