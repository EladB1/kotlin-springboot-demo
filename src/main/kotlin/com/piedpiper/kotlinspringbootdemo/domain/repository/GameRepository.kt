package com.piedpiper.kotlinspringbootdemo.domain.repository

import com.piedpiper.kotlinspringbootdemo.domain.model.Game
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : MongoRepository<Game, String> {
    fun findByGameId(gameId: String): Game?
    fun findByName(name: String): Game?
}