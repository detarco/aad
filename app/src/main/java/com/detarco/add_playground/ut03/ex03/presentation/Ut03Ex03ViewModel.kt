package com.detarco.add_playground.ut03.ex03.presentation

import com.detarco.add_playground.ut03.ex03.domain.GetAlertsUseCase
import com.detarco.add_playground.ut03.ex03.domain.FindAlertUseCase
import androidx.lifecycle.ViewModel

class Ut03Ex03ViewModel(private val getAlertsUseCase: GetAlertsUseCase,
                        private val findAlertUseCase: FindAlertUseCase
) : ViewModel() {

    fun getAlerts() = getAlertsUseCase.execute()

    fun findAlert(alertId: String) = findAlertUseCase.execute(alertId)
}