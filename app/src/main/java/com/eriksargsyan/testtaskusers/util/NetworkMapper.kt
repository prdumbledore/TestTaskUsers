package com.eriksargsyan.testtaskusers.util

import com.eriksargsyan.testtaskusers.model.data.net.FriendsNet
import com.eriksargsyan.testtaskusers.model.data.net.UserNet
import com.eriksargsyan.testtaskusers.model.domain.EyeColor
import com.eriksargsyan.testtaskusers.model.domain.Fruit
import com.eriksargsyan.testtaskusers.model.domain.Gender
import com.eriksargsyan.testtaskusers.model.domain.User
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<UserNet, User> {
    override fun fromEntityToDomain(entity: UserNet): User {
        return User(
            id = entity.id,
            isActive = entity.isActive,
            age = entity.age,
            eyeColor = when (entity.eyeColor) {
                "brown" -> EyeColor.COLOR_BROWN
                "green" -> EyeColor.COLOR_GREEN
                else -> EyeColor.COLOR_BLUE
            },
            name = entity.name,
            gender = when (entity.gender) {
                "male" -> Gender.MALE
                else -> Gender.FEMALE
            },
            company = entity.company,
            email = entity.email,
            phone = entity.phone,
            address = entity.address,
            about = entity.about,
            registered = entity.registered,
            latitude = entity.latitude,
            longitude = entity.longitude,
            favoriteFruit = when (entity.favoriteFruit) {
                "apple" -> Fruit.APPLE
                "banana" -> Fruit.BANANA
                else -> Fruit.STRAWBERRY
            },
            friends = entity.friends.map { it.id }
        )
    }

    override fun toEntityFromDomain(domainModel: User): UserNet {
        return UserNet(
            id = domainModel.id,
            isActive = domainModel.isActive,
            age = domainModel.age,
            eyeColor =domainModel.eyeColor.str,
            name = domainModel.name,
            gender = domainModel.gender.str,
            company = domainModel.company,
            email = domainModel.email,
            phone = domainModel.phone,
            address = domainModel.address,
            about = domainModel.about,
            registered = domainModel.registered,
            latitude = domainModel.latitude,
            longitude = domainModel.longitude,
            favoriteFruit = domainModel.favoriteFruit.str,
            friends = domainModel.friends.map { FriendsNet(it) }
        )
    }

    fun fromEntityToDomainList(entities: List<UserNet>): List<User> {
        return entities.map { fromEntityToDomain(it) }
    }

}