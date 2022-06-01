package com.eriksargsyan.testtaskusers.repository

import com.eriksargsyan.testtaskusers.model.database.UserDao
import com.eriksargsyan.testtaskusers.model.domain.User
import com.eriksargsyan.testtaskusers.model.util.DatabaseMapper
import com.eriksargsyan.testtaskusers.model.util.NetworkMapper
import com.eriksargsyan.testtaskusers.viewmodel.UserAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor (
    private val userAPI: UserAPI,
    private val userDao: UserDao,
    private val databaseMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper
        ) {

    suspend fun getUsers(): List<User> {
        val userDB = userDao.getAllUsers()
        return databaseMapper.fromEntityToDomainList(userDB)
    }

    suspend fun getUser(id: Int): User {
        val userDB = userDao.getById(id)
        return databaseMapper.fromEntityToDomain(userDB)
    }

    suspend fun cacheAndGet():  List<User> {
        val userNet = userAPI.getUsers()
        val users = networkMapper.fromEntityToDomainList(userNet)
        for (user in users) {
            userDao.insertUser(databaseMapper.toEntityFromDomain(user))
        }
        val userDB = userDao.getAllUsers()
        return databaseMapper.fromEntityToDomainList(userDB)
    }

}