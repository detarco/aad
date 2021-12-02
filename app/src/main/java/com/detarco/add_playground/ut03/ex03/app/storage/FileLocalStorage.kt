package com.detarco.add_playground.ut03.ex03.app.storage

import com.detarco.add_playground.ut03.ex03_b.ex03.app.storage.LocalModel
import com.detarco.add_playground.ut03.ex03_b.ex03.app.storage.LocalStorage

class FileLocalStorage<T: LocalModel> : LocalStorage<T> {
    override fun fetch(modelId: String, typeClass: Class<T>): T? {
        TODO("Not yet implemented")
    }

    override fun save(model: T, typeClass: Class<T>) {
        TODO("Not yet implemented")
    }
}