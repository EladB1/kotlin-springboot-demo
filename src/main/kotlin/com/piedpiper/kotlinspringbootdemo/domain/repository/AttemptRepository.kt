package com.piedpiper.kotlinspringbootdemo.domain.repository

import com.piedpiper.kotlinspringbootdemo.domain.model.ChallengeRunAttempt
import com.piedpiper.kotlinspringbootdemo.domain.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AttemptRepository : MongoRepository<ChallengeRunAttempt, String> {
    fun findAllAttemptsByAttemptBy(user: User): List<ChallengeRunAttempt>
    fun findAttemptsByRunId(runId: String): List<ChallengeRunAttempt>
}