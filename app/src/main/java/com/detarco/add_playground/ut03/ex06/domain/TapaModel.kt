package com.detarco.add_playground.ut03.ex06.domain

data class CompetitionModel(val id: String,
                            /**
                             * Cambio para poder usar string, mirar como guardar Date y LocalDate
                             */
                            val start: String,
                            val end: String,
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
