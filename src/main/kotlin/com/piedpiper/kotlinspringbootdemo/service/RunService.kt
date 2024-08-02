package com.piedpiper.kotlinspringbootdemo.service

import com.piedpiper.kotlinspringbootdemo.domain.model.ChallengeRun
import com.piedpiper.kotlinspringbootdemo.domain.repository.RunRepository
import com.piedpiper.kotlinspringbootdemo.error.EntityExistsError
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RunService(val runRepository: RunRepository) {
    fun getAllRuns(): List<ChallengeRun> {
        return runRepository.findAll()
    }

    fun getAllRunsByGameId(gameId: String): List<ChallengeRun> {
        return runRepository.findAllByGameId(gameId)
    }

    fun getRunById(runId: String): ChallengeRun? {
        return runRepository.findByRunId(runId)
    }

    fun getRunByName(runName: String): ChallengeRun? {
        return runRepository.findByRunName(runName)
    }

    fun createRun(gameId: String, runName: String, description: String): ChallengeRun? {
        if (runRepository.findByRunName(runName) != null)
            throw EntityExistsError("Run with name $runName already exists")
        return runRepository.save(ChallengeRun(gameId=gameId, runName=runName, description=description, created=LocalDateTime.now()))
    }
}