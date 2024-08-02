package com.piedpiper.kotlinspringbootdemo.controller

import com.piedpiper.kotlinspringbootdemo.domain.model.ChallengeRun
import com.piedpiper.kotlinspringbootdemo.service.RunService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class RunController(val runService: RunService) {
    @QueryMapping
    fun getAllChallengeRuns(): List<ChallengeRun> {
        return runService.getAllRuns()
    }

    @QueryMapping
    fun getChallengeRunsByGameId(@Argument gameId: String): List<ChallengeRun> {
        return runService.getAllRunsByGameId(gameId)
    }

    @QueryMapping
    fun getChallengeRunById(@Argument runId: String): ChallengeRun? {
        return runService.getRunById(runId)
    }

    @QueryMapping
    fun getChallengeRunByName(@Argument runName: String): ChallengeRun? {
        return runService.getRunByName(runName)
    }

    @MutationMapping
    fun createChallengeRun(@Argument gameId: String, @Argument runName: String, @Argument description: String): ChallengeRun? {
        return runService.createRun(gameId, runName, description)
    }
}