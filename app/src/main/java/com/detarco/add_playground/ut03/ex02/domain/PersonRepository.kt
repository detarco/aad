package com.detarco.add_playground.ut03.ex02.domain

interface PersonRepository {

    fun savePerson(personModel: PersonModel)
    fun fetchAll(): List<PersonModel>

}