package com.piedpiper.kotlinspringbootdemo.controller

import com.piedpiper.kotlinspringbootdemo.domain.model.User
import com.piedpiper.kotlinspringbootdemo.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserController(val userService: UserService) {
    @QueryMapping
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    @QueryMapping
    fun getUserById(@Argument userId: String): User {
        return userService.getUserById(userId)
    }

    @QueryMapping
    fun getUserByUsername(@Argument username: String): User {
        return userService.getUserByUsername(username)
    }

    @MutationMapping
    fun createUser(@Argument username: String): User {
        return userService.createUser(username)
    }

    @MutationMapping
    fun addFriend(@Argument userId: String, @Argument friendUserId: String): User {
        return userService.addFriend(userId, friendUserId)
    }

    @MutationMapping
    fun removeFriend(@Argument userId: String, @Argument friendUserId: String): User {
        return userService.removeFriend(userId, friendUserId)
    }
}