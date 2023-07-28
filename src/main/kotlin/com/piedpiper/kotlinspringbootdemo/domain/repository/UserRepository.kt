package com.piedpiper.kotlinspringbootdemo.domain.repository

import com.piedpiper.kotlinspringbootdemo.domain.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String>