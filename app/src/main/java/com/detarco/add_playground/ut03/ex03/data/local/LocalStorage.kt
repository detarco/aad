package com.detarco.add_playground.ut03.ex03.data.local

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.Serializer

interface LocalStorage<T : LocalModel> {
    fun save(models: List<T>)
    fun findById(alertId: String): T
    fun findAll(): List<T>
}

class SharPrefLocalStorage<T : LocalModel>(
    private val activity: AppCompatActivity,
    private val serializer: Serializer<T>
) : LocalStorage<T> {

    private val sharedPref = activity.getSharedPreferences(
        activity.getString(R.string.ut03_preference_file_exercise03), Context.MODE_PRIVATE
    )

    override fun save(models: List<T>) {
        val edit = sharedPref.edit()
        edit?.putString(models.getAlertId(), serializer.toJson.(models))
        edit?.apply()
    }

    override fun findById(alertId: String): T {
        val jsonModel = sharedPref.getString(id, "{}")
        return if (jsonModel != null) {
            serializer.fromJson(jsonModel)
        } else {
            null
        }
    }

    override fun findAll(): List<T> {
        val jsonModel = sharedPref.getString(id, "{}")
    }
}




