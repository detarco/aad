package com.detarco.add_playground.ut03.ex06.data.local

import com.detarco.add_playground.ut03.ex06.domain.TapaModel

interface TapaLocalSource {

    fun getTapas(): Result<List<TapaModel>>

    fun getTapaById(tapaId: String): Result<TapaModel>

    fun save(tapaModel: TapaModel): Result<Boolean>

    fun save(tapaModels: List<TapaModel>): Result<Boolean>

}