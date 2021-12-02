package com.detarco.add_playground.ut03.ex03.domain

import com.detarco.add_playground.ut03.ex03_v2.domain.FileModel

data class AlertModel(
    val id: String,
    val title: String,
    val type: Int,
    val summary: String,
    val datePublished: String,
    val body: String,
    val source: String,
    val files: List<FileModel>
)

data class FileModel(val name: String, val url: String)
