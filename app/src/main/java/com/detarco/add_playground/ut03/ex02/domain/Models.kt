package com.detarco.add_playground.ut03.ex02.domain

data class PersonModel (
    //val id: Int?= 0,
    val id: Int = 0,
    val name: String,
    val age: Int,
    val address: String?,
    val petModel: PetModel,
    //Relación de 1 a N
    val carModel: MutableList<CarModel>
    )

data class PetModel (
    //val id: Int? = 0,
    val id: Int = 0,
    val name: String,
    val age: Int
    )

data class CarModel(
    val id: Int,
    val brand: String,
    val  model: String
    )