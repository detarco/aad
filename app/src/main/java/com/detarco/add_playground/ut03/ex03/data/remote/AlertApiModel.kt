package com.detarco.add_playground.ut03.ex03.data.remote

import com.detarco.add_playground.ut03.ex03_v2.domain.AlertModel

data class AlertApiModel(
    val alert_id: String,
    val title: String,
    val summary: String,
    val type: String,
    val date: String,
    val body: String? = "",
    val source: String? = "",
) {
    fun toDomainModel(): AlertModel = AlertModel(
        alert_id,
        title,
        type.toInt(),
        summary,
        date,
        body ?: "",
        source ?: "",
        emptyList()
    )
}