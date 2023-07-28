package com.piedpiper.kotlinspringbootdemo.domain.model

data class User(val userId: String, val username: String, val friends: List<User>)
