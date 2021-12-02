package com.detarco.add_playground.ut03.ex03.app.storage

import com.detarco.add_playground.ut03.ex03_b.ex03.app.storage.LocalModel

interface LocalStorage<T : LocalModel> {
    fun fetch(modelId: String, typeClass: Class<T>): T?
    fun save(model: T, typeClass: Class<T>)
}