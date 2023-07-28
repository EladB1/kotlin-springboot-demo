package com.piedpiper.kotlinspringbootdemo.service

import com.piedpiper.kotlinspringbootdemo.domain.repository.UserRepository

class UserService(val userRepository: UserRepository) {
}