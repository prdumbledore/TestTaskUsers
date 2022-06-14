package com.eriksargsyan.testtaskusers.model.repository

import com.eriksargsyan.testtaskusers.model.database.UserDao
import com.eriksargsyan.testtaskusers.model.domain.User
import com.eriksargsyan.testtaskusers.model.net.UserAPI
import com.eriksargsyan.testtaskusers.util.DatabaseMapper
import com.eriksargsyan.testtaskusers.util.NetworkMapper
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor (
    private val userAPI: UserAPI,
    private val userDao: UserDao,
    private val databaseMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper
        ): UserRepository {

    override suspend fun getUsers(): List<User> {
        val userDB = userDao.getAllUsers()
        return databaseMapper.fromEntityToDomainList(userDB)
    }

    override suspend fun getUser(id: Int): User {
        val userDB = userDao.getById(id)
        return databaseMapper.fromEntityToDomain(userDB)
    }

    override suspend fun cacheAndGet():  List<User> {
        val userNet = userAPI.getUsers()
        val users = networkMapper.fromEntityToDomainList(userNet)
        for (user in users) {
            userDao.insertUser(databaseMapper.toEntityFromDomain(user))
        }
        val userDB = userDao.getAllUsers()
        return databaseMapper.fromEntityToDomainList(userDB)
    }

}