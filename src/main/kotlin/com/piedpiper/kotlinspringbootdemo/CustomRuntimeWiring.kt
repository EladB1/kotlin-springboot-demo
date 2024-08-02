package com.piedpiper.kotlinspringbootdemo

import graphql.schema.idl.RuntimeWiring
import org.springframework.graphql.execution.RuntimeWiringConfigurer
import org.springframework.stereotype.Component

@Component
class CustomRuntimeWiring(): RuntimeWiringConfigurer {
    override fun configure(builder: RuntimeWiring.Builder) {
        builder
            .scalar(Scalars.localDateTimeType())
            .build();
    }
}