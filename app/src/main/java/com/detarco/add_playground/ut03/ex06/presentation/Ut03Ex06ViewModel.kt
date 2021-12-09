package com.detarco.add_playground.ut03.ex06.presentation

import androidx.lifecycle.ViewModel
import com.detarco.add_playground.ut03.ex06.data.TapaDataRepository
import com.detarco.add_playground.ut03.ex06.data.local.TapaLocalSource
import com.detarco.add_playground.ut03.ex06.data.remote.MockDataSource
import com.detarco.add_playground.ut03.ex06.domain.GetTapaUseCase
import com.detarco.add_playground.ut03.ex06.domain.GetTapasUseCase

class Ut03Ex06ViewModel : ViewModel() {

    fun loadTapas(): TapasViewState {
        val useCase = GetTapasUseCase(
            TapaDataRepository(
                MockDataSource(),
                TapaLocalSource
            )
        )
        val result = useCase.execute()
        return TapasViewState(false, result.getOrNull(), result.exceptionOrNull())
    }

    fun loadTapa(tapaId: String): TapaViewState {
        val useCase = GetTapaUseCase(
            TapaDataRepository(
                MockDataSource(),
                TapaLocalSource
            )
        )
        val result = useCase.execute(tapaId)
        return TapaViewState(false, result.getOrNull(), result.exceptionOrNull())
    }
}