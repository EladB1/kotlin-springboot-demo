package com.piedpiper.kotlinspringbootdemo.domain.repository

import com.piedpiper.kotlinspringbootdemo.domain.model.Game
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : MongoRepository<Game, Int> {
    fun findByGameId(gameId: String): Game?
}