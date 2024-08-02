package com.piedpiper.kotlinspringbootdemo.domain.repository

import com.piedpiper.kotlinspringbootdemo.domain.model.ChallengeRun
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RunRepository : MongoRepository<ChallengeRun, String> {
    fun findByRunId(runId: String) : ChallengeRun?
    fun findAllByGameId(gameId: String) : List<ChallengeRun>
    fun findByRunName(runName: String): ChallengeRun?
}