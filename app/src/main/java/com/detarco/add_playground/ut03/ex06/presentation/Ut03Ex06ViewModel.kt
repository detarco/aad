package com.detarco.add_playground.ut03.ex06.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.detarco.add_playground.commons.serializer.GsonSerializer
import com.detarco.add_playground.ut03.ex06.data.TapaDataRepository
import com.detarco.add_playground.ut03.ex06.data.local.files.TapaFileLocalSource
import com.detarco.add_playground.ut03.ex06.data.remote.MockDataSource
import com.detarco.add_playground.ut03.ex06.domain.GetTapaUseCase
import com.detarco.add_playground.ut03.ex06.domain.GetTapasUseCase
import com.detarco.add_playground.ut03.ex06.domain.TapaModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Ut03Ex06ViewModel @Inject constructor(
    private val getTapaUseCase: GetTapaUseCase,
    private val getTapasUseCase: GetTapasUseCase
) : ViewModel() {

    fun loadTapas(): TapasViewState {
        val result = getTapasUseCase.execute()
        return TapasViewState(false, result.getOrNull(), result.exceptionOrNull())
    }

    fun loadTapa(tapaId: String): TapaViewState {
        val result = getTapaUseCase.execute(tapaId)
        return TapaViewState(false, result.getOrNull(), result.exceptionOrNull())
    }
}