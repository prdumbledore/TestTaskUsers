package com.eriksargsyan.testtaskusers.di

import com.eriksargsyan.testtaskusers.model.repository.UserRepository
import com.eriksargsyan.testtaskusers.model.repository.UserRepositoryImpl
import com.eriksargsyan.testtaskusers.util.Dispatchers
import com.eriksargsyan.testtaskusers.util.IO
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}