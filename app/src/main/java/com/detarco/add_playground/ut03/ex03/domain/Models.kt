package com.detarco.add_playground.ut03.ex03.domain

data class AlertModel(
    val id: String,
    val title: String,
    val type: Int,
    val summary: String,
    val datePublished: String,
    val body: String,
    val source: String,
    val files: List<FileModel>
)

data class FileModel(val name: String, val url: String)

data class CustomerModel(
    val id: Int = 0,
    val name: String,
    val age: Int,
    //Relación de 1 a N
    val clothesModel: MutableList<ClothesModel>
)

data class ClothesModel(
    val id: Int = 0,
    val type: String
)