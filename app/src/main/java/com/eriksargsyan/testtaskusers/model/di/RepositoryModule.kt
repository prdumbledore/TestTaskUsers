package com.eriksargsyan.testtaskusers.model.di

import com.eriksargsyan.testtaskusers.model.database.UserDao
import com.eriksargsyan.testtaskusers.repository.UserRepository
import com.eriksargsyan.testtaskusers.model.util.DatabaseMapper
import com.eriksargsyan.testtaskusers.model.util.NetworkMapper
import com.eriksargsyan.testtaskusers.viewmodel.UserAPI
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
        return UserRepository(userAPI, userDao, databaseMapper, networkMapper)
    }
}