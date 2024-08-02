package com.piedpiper.kotlinspringbootdemo.domain

import com.piedpiper.kotlinspringbootdemo.error.DataError
import graphql.schema.Coercing
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeScalar : Coercing<LocalDateTime, String> {
    override fun serialize(dataFetcherResult: Any): String {
        if (dataFetcherResult is LocalDateTime)
            return dataFetcherResult.format(DateTimeFormatter.ISO_DATE_TIME)
        throw DataError("Not a valid date")
    }

    override fun parseValue(input: Any): LocalDateTime {
        return LocalDateTime.parse(input.toString(), DateTimeFormatter.ISO_DATE_TIME)
    }

    override fun parseLiteral(input: Any): LocalDateTime {
        if (input is String) {
            return LocalDateTime.parse(input, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
        throw DataError("Value is not a valid ISO datetime")
    }
}