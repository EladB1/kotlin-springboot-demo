package com.piedpiper.kotlinspringbootdemo.domain.model

import lombok.NonNull
import lombok.RequiredArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id val userId: String?,
    @NonNull val username: String,
    val friendIds: List<String> = emptyList()
) {
    constructor(
        username: String,
        friendIds: List<String> = emptyList()
    ): this(null, username, friendIds)
}
