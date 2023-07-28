package com.piedpiper.kotlinspringbootdemo.domain.model

import lombok.NonNull
import lombok.RequiredArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@RequiredArgsConstructor
@Document
data class Game(@Id val gameId: String?, @NonNull val name: String, val platforms: List<Platform> = emptyList()) { constructor(name: String, platforms: List<Platform> = emptyList()): this(null, name, platforms)
}
