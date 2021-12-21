package com.detarco.add_playground.ut03.ex06.data.remote

import com.detarco.add_playground.ut03.ex06.domain.TapaModel

interface RemoteDataSource {
    fun getTapas(): Result<List<TapaModel>>
    fun getTapa(tapaId: String): Result<TapaModel>
}