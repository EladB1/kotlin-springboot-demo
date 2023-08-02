package com.piedpiper.kotlinspringbootdemo.service

import com.piedpiper.kotlinspringbootdemo.domain.model.Game
import com.piedpiper.kotlinspringbootdemo.domain.model.Platform
import com.piedpiper.kotlinspringbootdemo.domain.repository.GameRepository
import com.piedpiper.kotlinspringbootdemo.error.EntityExistsError
import com.piedpiper.kotlinspringbootdemo.error.NotFoundError
import org.springframework.stereotype.Service

@Service
class GameService(val gameRepository: GameRepository) {
    fun getAllGames(): List<Game> {
        return gameRepository.findAll()
    }

    fun getGameById(id: String): Game {
        return gameRepository.findByGameId(id) ?: throw NotFoundError("Could not find game with id $id")
    }

    fun getByName(name: String): Game {
        return gameRepository.findByName(name) ?: throw NotFoundError("Could not find game with name $name")
    }

    fun createGame(name: String, platforms: List<Platform> = emptyList()): Game {
        if (gameRepository.findByName(name) != null)
            throw EntityExistsError("Game named $name already exists")
        return gameRepository.save(Game(name=name, platforms=platforms))
    }

    fun addPlatformToGame(gameId: String, platform: Platform): Game {
        val game: Game = this.getGameById(gameId)
        if (platform in game.platforms)
            return game
        val gameCopy = game.copy(platforms = game.platforms + platform)
        return gameRepository.save(gameCopy)
    }
}