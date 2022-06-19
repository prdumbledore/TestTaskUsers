package com.eriksargsyan.testtaskusers.util

import com.eriksargsyan.testtaskusers.model.data.network.FriendsNet
import com.eriksargsyan.testtaskusers.model.data.network.UserNet
import com.eriksargsyan.testtaskusers.model.data.domain.EyeColor
import com.eriksargsyan.testtaskusers.model.data.domain.Fruit
import com.eriksargsyan.testtaskusers.model.data.domain.Gender
import com.eriksargsyan.testtaskusers.model.data.domain.User
import javax.inject.Inject

class UserNetworkMapper @Inject constructor() : EntityMapper<UserNet, User> {
    override fun fromEntityToDomain(entity: UserNet): User {
        return User(
            id = entity.id,
            isActive = entity.isActive,
            age = entity.age,
            eyeColor = EyeColor.fromStr(entity.eyeColor),
            name = entity.name,
            gender = Gender.fromStr(entity.gender),
            company = entity.company,
            email = entity.email,
            phone = entity.phone,
            address = entity.address,
            about = entity.about,
            registered = entity.registered,
            latitude = entity.latitude,
            longitude = entity.longitude,
            favoriteFruit = Fruit.fromStr(entity.favoriteFruit),
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