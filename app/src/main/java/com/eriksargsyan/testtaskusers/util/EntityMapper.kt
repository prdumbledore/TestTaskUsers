package com.eriksargsyan.testtaskusers.util

interface EntityMapper <Entity, DomainModel> {

    fun fromEntityToDomain(entity: Entity): DomainModel

    fun toEntityFromDomain(domainModel: DomainModel): Entity
}