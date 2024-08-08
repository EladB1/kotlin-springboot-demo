package com.piedpiper.kotlinspringbootdemo.service

import io.mockk.every
import org.junit.jupiter.api.Test
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

import com.piedpiper.kotlinspringbootdemo.domain.model.Game
import com.piedpiper.kotlinspringbootdemo.domain.model.Platform
import com.piedpiper.kotlinspringbootdemo.domain.repository.GameRepository
import com.piedpiper.kotlinspringbootdemo.error.EntityExistsError
import com.piedpiper.kotlinspringbootdemo.error.NotFoundError

class GameServiceTest {
    private val gameRepository: GameRepository = mockk()
    private val gameService = GameService(gameRepository)

    @Test
    fun getAllGamesEmpty() {
        every { gameRepository.findAll() } returns emptyList()
        val result = gameService.getAllGames()
        assertTrue(result.isEmpty())
    }

    @Test
    fun getAllGamesNonEmpty() {
        val games: List<Game> = listOf(Game("Terraria", listOf(Platform.PC)), Game("FTL", listOf(Platform.PC)))
        every { gameRepository.findAll() } returns games
        val result = gameService.getAllGames()
        assertFalse { result.isEmpty() }
        assertEquals(2, result.size)
        assertEquals(games, result)
    }

    @Test
    fun getGameByIdNotFound() {
        every { gameRepository.findByGameId(any(String::class)) } returns null
        val error: NotFoundError = assertThrows<NotFoundError> { gameService.getGameById("someId") }
        assertEquals("Could not find game with id someId", error.message)
    }

    @Test
    fun getGameByIdFound() {
        every { gameRepository.findByGameId("someId") } returns Game("someId", "someName", listOf(Platform.PS4, Platform.PS5))
        val result: Game = gameService.getGameById("someId")
        assertDoesNotThrow { NotFoundError::class }
        assertEquals("someId", result.gameId)
        assertEquals("someName", result.name)
        assertEquals(2, result.platforms.size)
    }

    @Test
    fun getGameByNameNotFound() {
        every { gameRepository.findByName(any(String::class)) } returns null
        val error: NotFoundError = assertThrows<NotFoundError> { gameService.getByName("Silent Hills") }
        assertEquals("Could not find game with name Silent Hills", error.message)
    }

    @Test
    fun getGameByNameFound() {
        every { gameRepository.findByName("someName") } returns Game("someId", "someName", listOf(Platform.PS4, Platform.PS5))
        val result: Game = gameService.getByName("someName")
        assertDoesNotThrow { NotFoundError::class }
        assertEquals("someId", result.gameId)
        assertEquals("someName", result.name)
        assertEquals(2, result.platforms.size)
    }

    @Test
    fun createGameAlreadyExists() {
        every { gameRepository.findByName("someName") } returns Game("someId", "someName", listOf(Platform.Switch))
        val error: EntityExistsError = assertThrows { gameService.createGame("someName", listOf(Platform.Switch)) }
        assertEquals("Game named someName already exists", error.message)
    }

    @Test
    fun createGameSuccessful() {
        every { gameRepository.findByName("someName") } returns null
        every { gameRepository.save(any(Game::class)) } returns Game("someId", "someName", listOf(Platform.Switch))
        val result: Game = gameService.createGame("someName", listOf(Platform.Switch))
        assertDoesNotThrow { EntityExistsError::class }
        assertEquals("someId", result.gameId)
        assertEquals("someName", result.name)
        assertEquals(1, result.platforms.size)
        assertEquals(Platform.Switch, result.platforms[0])
    }

    @Test
    fun addPlatformToGameNotFound() {
        every { gameRepository.findByGameId("someId") } returns null
        val error: NotFoundError = assertThrows { gameService.addPlatformToGame("someId", Platform.Xbox) }
        assertEquals("Could not find game with id someId", error.message)
    }

    @Test
    fun addPlatformToGameSuccessful() {
        val gameOriginal = Game("someId", "someName", listOf(Platform.PS4))
        val gameModified = gameOriginal.copy(platforms = listOf(Platform.PS4, Platform.PC))
        every { gameRepository.findByGameId("someId") } returns gameOriginal
        every { gameRepository.save(any(Game::class)) } returns gameModified
        val result: Game = gameService.addPlatformToGame("someId", Platform.PC)
        assertDoesNotThrow { NotFoundError::class }
        assertEquals(gameModified, result)
        verify(exactly = 1) { gameRepository.save(any(Game::class)) }
    }

    @Test
    fun addPlatformToGameNotNeeded() {
        val gameOriginal = Game("someId", "someName", listOf(Platform.PS4))
        every { gameRepository.findByGameId("someId") } returns gameOriginal
        val result: Game = gameService.addPlatformToGame("someId", Platform.PS4)
        assertDoesNotThrow { NotFoundError::class }
        assertEquals(gameOriginal, result)
        verify(exactly = 0) { gameRepository.save(any(Game::class)) }
    }
}