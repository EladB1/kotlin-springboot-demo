package com.piedpiper.kotlinspringbootdemo.domain.model

import lombok.NonNull
import lombok.RequiredArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@RequiredArgsConstructor
@Document
data class ChallengeRun(
    @Id val runId: String?,
    @NonNull val gameId: String,
    @NonNull val runName: String,
    @NonNull val description: String,
    @NonNull val created: LocalDateTime
) {
    constructor(
        gameId: String,
        runName: String,
        description: String,
        created: LocalDateTime
    ): this(null, gameId, runName, description, created)
}
