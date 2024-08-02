package com.piedpiper.kotlinspringbootdemo.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ChallengeRunAttempt(
    @Id val attemptId: String,
    val runId: String,
    val status: RunStatus
)
