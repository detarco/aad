package com.detarco.add_playground.ut03.ex03.app.storage

import android.content.Context
import com.detarco.add_playground.ut03.ex03.app.serializer.JsonSerializer
import com.detarco.add_playground.ut03.ex03_b.ex03.app.storage.LocalModel
import com.detarco.add_playground.ut03.ex03_b.ex03.app.storage.LocalStorage

class SharPrefLocalStorage<T : LocalModel>(
    private val context: Context,
    private val jsonSerializer: JsonSerializer,
    private val nameXmlFile: String
) : LocalStorage<T> {

    private val sharedPref = context.getSharedPreferences(nameXmlFile, Context.MODE_PRIVATE)

    override fun fetch(modelId: String, typeClass: Class<T>): T? {
        val jsonModel = sharedPref.getString(modelId, "{}")
        return if (jsonModel != null) {
            jsonSerializer.fromJson(jsonModel, typeClass)
        } else {
            null
        }
    }

    override fun save(model: T, typeClass: Class<T>) {
        val edit = sharedPref.edit()
        edit?.putString(model.getId(), jsonSerializer.toJson(model, typeClass))
        edit?.apply()
    }
}