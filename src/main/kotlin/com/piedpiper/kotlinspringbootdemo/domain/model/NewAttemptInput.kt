package com.piedpiper.kotlinspringbootdemo.domain.model

data class NewAttemptInput(
    val runId: String,
    val attemptByUserId: String,
    val immediate: Boolean
)
