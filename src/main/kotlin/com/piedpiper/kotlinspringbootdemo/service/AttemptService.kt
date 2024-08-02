package com.piedpiper.kotlinspringbootdemo.service

import com.piedpiper.kotlinspringbootdemo.domain.model.ChallengeRunAttempt
import com.piedpiper.kotlinspringbootdemo.domain.model.NewAttemptInput
import com.piedpiper.kotlinspringbootdemo.domain.model.RunStatus
import com.piedpiper.kotlinspringbootdemo.domain.model.User
import com.piedpiper.kotlinspringbootdemo.domain.repository.AttemptRepository
import com.piedpiper.kotlinspringbootdemo.domain.repository.UserRepository
import com.piedpiper.kotlinspringbootdemo.error.DataError
import com.piedpiper.kotlinspringbootdemo.error.NotFoundError
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrElse

@Service
class AttemptService(val attemptRepo: AttemptRepository, val userRepo: UserRepository) {
    fun findAllAttempts(): List<ChallengeRunAttempt> {
        return attemptRepo.findAll()
    }

    fun findAllAttemptsByUserId(userId: String): List<ChallengeRunAttempt> {
        val user: User = userRepo.findByUserId(userId)
            ?: throw NotFoundError("Could not find user with id '$userId'")
        return attemptRepo.findAllAttemptsByAttemptBy(user)
    }

    fun findAllAttemptsByRunId(runId: String): List<ChallengeRunAttempt> {
        return attemptRepo.findAttemptsByRunId(runId)
    }

    fun createAttempt(input: NewAttemptInput): ChallengeRunAttempt {
        val user: User = userRepo.findByUserId(input.attemptByUserId)
            ?: throw NotFoundError("Could not find user with id '${input.attemptByUserId}'")
        return attemptRepo.save(ChallengeRunAttempt(input.runId, user, input.immediate))
    }

    fun changeAttemptStatus(attemptId: String, newStatus: RunStatus): ChallengeRunAttempt {
        val storedAttempt: ChallengeRunAttempt? = attemptRepo.findById(attemptId).getOrElse { throw NotFoundError("Could not find challenge run attempt with id '$attemptId'") }
        var updatedAttempt: ChallengeRunAttempt? = null
        if (newStatus == storedAttempt?.status)
            return storedAttempt
        if (storedAttempt?.status == RunStatus.TODO && newStatus == RunStatus.InProgress)
            updatedAttempt = storedAttempt.copy(status = newStatus, started = LocalDateTime.now())
        if (storedAttempt?.status == RunStatus.InProgress && (newStatus == RunStatus.Completed || newStatus == RunStatus.Failed))
            updatedAttempt = storedAttempt.copy(status = newStatus, finished = LocalDateTime.now())
        if (updatedAttempt == null)
            throw DataError("Invalid change of run status: Cannot go from ${storedAttempt?.status} to $newStatus")
        return attemptRepo.save(updatedAttempt)

    }
}