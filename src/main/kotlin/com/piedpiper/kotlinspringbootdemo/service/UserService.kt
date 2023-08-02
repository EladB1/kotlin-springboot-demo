package com.piedpiper.kotlinspringbootdemo.service

import com.piedpiper.kotlinspringbootdemo.domain.model.User
import com.piedpiper.kotlinspringbootdemo.domain.repository.UserRepository
import com.piedpiper.kotlinspringbootdemo.error.EntityExistsError
import com.piedpiper.kotlinspringbootdemo.error.NotFoundError
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUserById(userId: String): User {
        return userRepository.findByUserId(userId) ?: throw NotFoundError("Could not find user with id $userId")
    }

    fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username) ?: throw NotFoundError("Could not find user with id $username")
    }

    fun createUser(username: String): User {
        if (userRepository.findByUsername(username) != null) {
            throw EntityExistsError("Username '$username' is already in use")
        }
        return userRepository.save(User(username))
    }

    fun addFriend(userId: String, friendUserId: String): User {
        var user = this.getUserById(userId)
        var friend = this.getUserById(friendUserId)
        if (friendUserId !in user.friendIds && friendUserId != userId) {
            user = user.copy(friendIds = user.friendIds + friendUserId)
            friend = friend.copy(friendIds = friend.friendIds + userId)
            userRepository.saveAll(listOf(user, friend))
        }
        return user
    }

    fun removeFriend(userId: String, friendUserId: String): User {
        var user = this.getUserById(userId)
        var friend = this.getUserById(friendUserId)
        if (friendUserId in user.friendIds) {
            val userFriendList = user.friendIds.toMutableList()
            userFriendList.remove(friendUserId)
            val friendFriendList = friend.friendIds.toMutableList()
            friendFriendList.remove(userId)
            user = user.copy(friendIds = userFriendList)
            friend = friend.copy(friendIds = friendFriendList)
            userRepository.saveAll(listOf(user, friend))
        }
        return user
    }
}