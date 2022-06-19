package com.eriksargsyan.testtaskusers.util

import com.eriksargsyan.testtaskusers.model.data.database.UserDB
import com.eriksargsyan.testtaskusers.model.data.domain.EyeColor
import com.eriksargsyan.testtaskusers.model.data.domain.Fruit
import com.eriksargsyan.testtaskusers.model.data.domain.Gender
import com.eriksargsyan.testtaskusers.model.data.domain.User
import javax.inject.Inject

class UserDatabaseMapper @Inject constructor(): EntityMapper<UserDB, User> {
    override fun fromEntityToDomain(entity: UserDB): User {
        return User(
            id = entity.id,
            isActive = entity.isActive,
            age = entity.age,
            eyeColor = entity.eyeColor,
            name = entity.name,
            gender = entity.gender,
            company = entity.company,
            email = entity.email,
            phone = entity.phone,
            address = entity.address,
            about = entity.about,
            registered = entity.registered,
            latitude = entity.latitude,
            longitude = entity.longitude,
            favoriteFruit = entity.favoriteFruit,
            friends = entity.friendIds
        )
    }

    override fun toEntityFromDomain(domainModel: User): UserDB {
        return UserDB(
            id = domainModel.id,
            isActive = domainModel.isActive,
            age = domainModel.age,
            eyeColor =domainModel.eyeColor,
            name = domainModel.name,
            gender = domainModel.gender,
            company = domainModel.company,
            email = domainModel.email,
            phone = domainModel.phone,
            address = domainModel.address,
            about = domainModel.about,
            registered = domainModel.registered,
            latitude = domainModel.latitude,
            longitude = domainModel.longitude,
            favoriteFruit = domainModel.favoriteFruit,
            friendIds = domainModel.friends
        )
    }

    fun fromEntityToDomainList(entities: List<UserDB>): List<User> {
        return entities.map { fromEntityToDomain(it) }
    }

}