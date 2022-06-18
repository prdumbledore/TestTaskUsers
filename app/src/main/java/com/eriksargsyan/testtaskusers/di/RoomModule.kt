package com.eriksargsyan.testtaskusers.di

import android.content.Context
import androidx.room.Room
import com.eriksargsyan.testtaskusers.model.database.UserDao
import com.eriksargsyan.testtaskusers.model.database.UserRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideUserDb(@ApplicationContext context: Context): UserRoomDatabase {
        return Room.databaseBuilder(
            context,
            UserRoomDatabase::class.java,
            UserRoomDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userRoomDatabase: UserRoomDatabase): UserDao {
        return userRoomDatabase.UserDao()
    }
}