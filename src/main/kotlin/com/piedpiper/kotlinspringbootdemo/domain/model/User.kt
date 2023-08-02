package com.piedpiper.kotlinspringbootdemo.domain.model

import lombok.NonNull
import lombok.RequiredArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@RequiredArgsConstructor
@Document
data class User(
    @Id val userId: String?,
    @NonNull val username: String,
    val friends: List<User> = emptyList()
) {
    constructor(
        username: String,
        friends: List<User> = emptyList()
    ): this(null, username, friends)
}
