package com.detarco.add_playground.ut03.ex03.presentation

import com.detarco.add_playground.ut03.ex03_b.ex03.app.api.RetrofitApiClient
import com.detarco.add_playground.ut03.ex03_b.ex03.data.AlertDataRepository
import com.detarco.add_playground.ut03.ex03_b.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03_b.ex03.data.remote.AlertRemoteSource
import com.detarco.add_playground.ut03.ex03_b.ex03.presentation.Ut03Ex03ViewModel
import com.detarco.add_playground.ut03.ex03_v2.domain.FindAlertUseCase
import com.detarco.add_playground.ut03.ex03_v2.domain.GetAlertsUseCase

class AlertViewModelFactory {

    companion object {
        fun build(alertLocalSource: AlertLocalSource): Ut03Ex03ViewModel {
            val repository =
                AlertDataRepository(alertLocalSource, AlertRemoteSource(RetrofitApiClient()))

            return com.detarco.add_playground.ut03.ex03_b.ex03.presentation.Ut03Ex03ViewModel(
                GetAlertsUseCase(repository),
                FindAlertUseCase(repository)
            )
        }
    }
}