package com.piedpiper.kotlinspringbootdemo.error

import graphql.ErrorType
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component

@Component
class GraphQLException : DataFetcherExceptionResolverAdapter() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (ex) {
            is NotFoundError -> toGraphQLError(ex)
            is EntityExistsError -> toGraphQLError(ex)
            else ->  super.resolveToSingleError(ex, env)
        }
    }

    private fun toGraphQLError(ex: Throwable): GraphQLError {
        log.warn("Exception while handling request ${ex.message}", ex)
        return GraphqlErrorBuilder.newError().message(ex.message).errorType(ErrorType.DataFetchingException).build()
    }
}