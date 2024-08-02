package com.piedpiper.kotlinspringbootdemo.controller

import com.piedpiper.kotlinspringbootdemo.domain.model.ChallengeRunAttempt
import com.piedpiper.kotlinspringbootdemo.domain.model.NewAttemptInput
import com.piedpiper.kotlinspringbootdemo.domain.model.RunStatus
import com.piedpiper.kotlinspringbootdemo.service.AttemptService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class AttemptController(val attemptService: AttemptService) {
    @QueryMapping
    fun findAllChallengeRunAttempts(): List<ChallengeRunAttempt> {
        return attemptService.findAllAttempts()
    }

    @QueryMapping
    fun findAllChangeRunAttemptsByUserId(userId: String): List<ChallengeRunAttempt> {
        return attemptService.findAllAttemptsByUserId(userId)
    }

    @QueryMapping
    fun findAllAttemptsByRunId(runId: String): List<ChallengeRunAttempt> {
        return attemptService.findAllAttemptsByRunId(runId)
    }

    @MutationMapping
    fun createChallengeRunAttempt(@Argument input: NewAttemptInput): ChallengeRunAttempt? {
        return attemptService.createAttempt(input)
    }

    @MutationMapping
    fun changeChallengeRunAttemptStatus(@Argument attemptId: String, @Argument newStatus: RunStatus): ChallengeRunAttempt? {
        return attemptService.changeAttemptStatus(attemptId, newStatus)
    }
}