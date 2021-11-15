package com.detarco.add_playground.ut03.ex03.data.local

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.Serializer
import com.detarco.add_playground.ut03.ex03.domain.LocalModel

interface LocalStorage<T : LocalModel> {
    fun save(model: T)
    fun fetch(id: String): T?
}

class SharPrefLocalStorage<T : LocalModel>(
    private val activity: AppCompatActivity,
    private val serializer: Serializer<T>
) : LocalStorage<T> {

    private val sharedPref = activity.getSharedPreferences(
        activity.getString(R.string.ut03_preference_file_exercise03), Context.MODE_PRIVATE
    )

    override fun save(model: T) {
        val edit = sharedPref.edit()
        edit?.putString(model.getId(), serializer.toJson(model))
        edit?.apply()
    }

    override fun fetch(id: String): T? {
        val jsonModel = sharedPref.getString(id, "{}")
        return if (jsonModel != null) {
            serializer.fromJson(jsonModel)
        } else {
            null
        }
    }
}