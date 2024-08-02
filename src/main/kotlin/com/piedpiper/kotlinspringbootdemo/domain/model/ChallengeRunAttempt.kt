package com.piedpiper.kotlinspringbootdemo.domain.model

import lombok.RequiredArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@RequiredArgsConstructor
@Document
data class ChallengeRunAttempt(
    @Id val attemptId: String?,
    val runId: String,
    val status: RunStatus,
    val attemptBy: User,
    val started: LocalDateTime?,
    val finished: LocalDateTime?
) {
    constructor(
        runId: String,
        attemptBy: User,
        startImmediate: Boolean
    ): this(null, runId, if (startImmediate) RunStatus.InProgress else RunStatus.TODO, attemptBy, if (startImmediate) LocalDateTime.now() else null, null)
}
