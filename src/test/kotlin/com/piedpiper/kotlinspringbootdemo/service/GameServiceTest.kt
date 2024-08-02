package com.piedpiper.kotlinspringbootdemo.service

import com.mongodb.assertions.Assertions.assertTrue
import com.piedpiper.kotlinspringbootdemo.domain.model.Game
import com.piedpiper.kotlinspringbootdemo.domain.repository.GameRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class, MockitoExtension::class)
class GameServiceTest(@InjectMocks val gameService: GameService, @MockBean(GameRepository::class) val gameRepository: GameRepository) {
    @Test
    fun getAllGamesEmpty() {
        Mockito.`when`(gameRepository.findAll()).thenReturn(emptyList())
        val games = gameService.getAllGames()
        assertTrue(games.isEmpty())
    }
}