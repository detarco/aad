package com.detarco.add_playground.ut03.ex06.domain

import java.util.*

data class CompetitionModel(val id: String,
                            val start: Date,
                            val end: Date,
                            val tapaModels: List<TapaModel>)

data class TapaModel(val id: String,
                     val name: String,
                     val description: String,
                     val price: Double,
                     val urlMainPhoto: String,
                     val barModel: BarModel)

data class BarModel(val id: String,
                    val name: String,
                    val address: String)
