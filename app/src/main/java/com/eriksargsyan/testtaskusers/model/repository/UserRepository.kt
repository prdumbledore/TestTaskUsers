package com.eriksargsyan.testtaskusers.model.repository

import com.eriksargsyan.testtaskusers.model.data.domain.User
import com.eriksargsyan.testtaskusers.model.database.UserDao
import com.eriksargsyan.testtaskusers.model.network.SettingsStorage
import com.eriksargsyan.testtaskusers.model.network.UserAPI
import com.eriksargsyan.testtaskusers.util.IO
import com.eriksargsyan.testtaskusers.util.UserDatabaseMapper
import com.eriksargsyan.testtaskusers.util.UserNetworkMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUser(id: Int): User
    suspend fun cacheAndGet(): List<User>
}

class UserRepositoryImpl @Inject constructor(
    private val userAPI: UserAPI,
    private val userDao: UserDao,
    private val settingsStorage: SettingsStorage,
    private val userDatabaseMapper: UserDatabaseMapper,
    private val userNetworkMapper: UserNetworkMapper,
    @IO private val dispatcherIO: CoroutineDispatcher
) : UserRepository {

    override suspend fun getUsers(): List<User> =
        withContext(dispatcherIO) {
            val userDB = userDao.getAllUsers()
            return@withContext userDatabaseMapper.fromEntityToDomainList(userDB)
        }


    override suspend fun getUser(id: Int): User =
        withContext(dispatcherIO) {
            val userDB = userDao.getById(id)
            return@withContext userDatabaseMapper.fromEntityToDomain(userDB)
        }

    override suspend fun cacheAndGet(): List<User> =
        withContext(dispatcherIO) {
            val token = settingsStorage.getToken() ?: throw IllegalStateException()
            val userNet = userAPI.getUsers(token = token)
            val users = userNetworkMapper.fromEntityToDomainList(userNet)
            launch { userDao.insertUsers(users.map { userDatabaseMapper.toEntityFromDomain(it) }) }
            return@withContext users
        }

}