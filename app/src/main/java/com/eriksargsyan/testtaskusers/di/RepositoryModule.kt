package com.eriksargsyan.testtaskusers.di

import com.eriksargsyan.testtaskusers.model.database.UserDao
import com.eriksargsyan.testtaskusers.model.net.UserAPI
import com.eriksargsyan.testtaskusers.model.repository.UserRepository
import com.eriksargsyan.testtaskusers.model.repository.UserRepositoryImpl
import com.eriksargsyan.testtaskusers.util.DatabaseMapper
import com.eriksargsyan.testtaskusers.util.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao,
        userAPI: UserAPI,
        databaseMapper: DatabaseMapper,
        networkMapper: NetworkMapper
    ): UserRepository {
        return UserRepositoryImpl(userAPI, userDao, databaseMapper, networkMapper)
    }
}