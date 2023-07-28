package com.piedpiper.kotlinspringbootdemo.controller

import com.piedpiper.kotlinspringbootdemo.domain.model.Game
import com.piedpiper.kotlinspringbootdemo.domain.model.Platform
import com.piedpiper.kotlinspringbootdemo.service.GameService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class GameController(val gameService: GameService) {
    @QueryMapping
    fun getAllGames(): List<Game> {
        return gameService.getAllGames()
    }

    @QueryMapping
    fun getGameById(@Argument gameId: String): Game {
        return gameService.getGameById(gameId)
    }

    @MutationMapping
    fun createGame(@Argument name: String, @Argument platforms: List<Platform> = emptyList()): Game {
        return gameService.createGame(name, platforms)
    }

    @MutationMapping
    fun addPlatformToGame(@Argument gameId: String, @Argument platform: Platform): Game {
        return gameService.addPlatformToGame(gameId, platform)
    }
}