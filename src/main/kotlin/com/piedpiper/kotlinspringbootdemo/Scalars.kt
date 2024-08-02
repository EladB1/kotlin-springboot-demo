package com.piedpiper.kotlinspringbootdemo

import com.piedpiper.kotlinspringbootdemo.domain.DateTimeScalar
import graphql.schema.GraphQLScalarType

class Scalars {
    companion object {
        fun localDateTimeType(): GraphQLScalarType {
            return GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("LocalDateTime type")
                .coercing(DateTimeScalar())
                .build();
        }
    }
}