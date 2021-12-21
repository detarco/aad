package com.detarco.add_playground.ut03.ex06.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.serializer.GsonSerializer
import com.detarco.add_playground.ut03.ex06.data.TapaDataRepository
import com.detarco.add_playground.ut03.ex06.data.local.db.TapaDBLocalSource
import com.detarco.add_playground.ut03.ex06.data.remote.MockDataSource
import com.detarco.add_playground.ut03.ex06.domain.GetTapaUseCase
import com.detarco.add_playground.ut03.ex06.domain.GetTapasUseCase
import com.google.gson.Gson

class Ut03Ex06Activity : AppCompatActivity() {

    private val TAG = Ut03Ex06Activity::class.java.simpleName

    private val tapaRepository = TapaDataRepository(
        MockDataSource(),
        TapaDBLocalSource(applicationContext)
    )

    private val viewModel: Ut03Ex06ViewModel by lazy {
        Ut03Ex06ViewModel(
            GetTapaUseCase(tapaRepository),
            GetTapasUseCase(tapaRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex06)
        init()
    }

    private fun init() {
        loadTapas()
        loadTapa()
    }

    private fun loadTapas() {
        Thread {
            renderUiTapas(viewModel.loadTapas())
        }.start()
    }

    private fun loadTapa() {
        Thread {
            renderUiTapa(viewModel.loadTapa("123"))
        }.start()
    }

    private fun renderUiTapas(tapasViewState: TapasViewState) {
        tapasViewState.tapaModels?.let {
            Log.d(TAG, "Tapas: $it")
        }
        tapasViewState.failure?.let {
            Log.d(TAG, "Error: $it")
        }
    }

    private fun renderUiTapa(tapaViewState: TapaViewState) {
        tapaViewState.tapaModels?.let {
            Log.d(TAG, "Tapas: $it")
        }
        tapaViewState.failure?.let {
            Log.d(TAG, "Error: $it")
        }
    }
}