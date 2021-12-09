package com.detarco.add_playground.ut03.ex06.data.local.xml

import android.content.Context
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.serializer.GsonSerializer
import com.detarco.add_playground.ut03.ex06.data.local.TapaLocalSource
import com.detarco.add_playground.ut03.ex06.domain.Failure
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

class TapaXmlLocalSource(
    context: Context,
    private val serializer: GsonSerializer
): TapaLocalSource{

    private val sharPref = context.getSharedPreferences(
        context.getString(R.string.ut03_preference_file_exercise03),
        Context.MODE_PRIVATE
    )

    override fun getTapas(): Result<List<TapaModel>> {
        return try {
            val tapaList: MutableList<TapaModel> = mutableListOf()
            sharPref.all.map {
                tapaList.add(it.value as TapaModel)
            }
            Result.success(tapaList)
        }catch (failure: Exception){
            Result.failure(Failure.XmlError)
        }
    }

    override fun getTapaById(tapaId: String): Result<TapaModel> {

        return getTapas().mapCatching { it.first{ item -> item.id == tapaId } }

    }

    override fun save(tapaModel: TapaModel): Result<Boolean> {
        return try {
            val edit = sharPref.edit()
            edit.putString(tapaModel.id, serializer.toJson(tapaModel, TapaModel::class.java))
            edit.apply()
            Result.success(true)
        }catch (failure: Exception){
            Result.failure(Failure.XmlError)
        }

    }

    override fun save(tapaModels: List<TapaModel>): Result<Boolean> {
        return try {
            clearXml()
            tapaModels.map {
                tapaModel ->
                save(tapaModel)
            }
            Result.success(true)
        }catch (failure: Exception){
            Result.failure(Failure.XmlError)
        }
    }

    override fun updateTapa(tapaModel: TapaModel): Result<Boolean> {
        return try{
            getTapas().mapCatching {
                val tapaList = it.toMutableList()
                val indexTapa = tapaList.indexOfFirst { item -> item.id == tapaModel.id }
                if (indexTapa >= 0) {
                    tapaList[indexTapa] = tapaModel
                } else {
                    tapaList.add(tapaModel)
                }
                save(tapaList)
                true
            }
        }catch (failure: Exception){
            Result.failure(Failure.XmlError)
        }
    }

    override fun removeTapa(tapaId: String): Result<Boolean> {
        return try {
            getTapas().mapCatching {
                val tapaList = it.toMutableList()
                val indexTapa = tapaList.indexOfFirst { item -> item.id == tapaId }
                if (indexTapa >= 0) {
                    tapaList.removeAt(indexTapa)
                }
                save(tapaList)
                true
            }
        }catch (failure: Exception){
            Result.failure(Failure.XmlError)
        }
    }

    private fun clearXml(): Result<Boolean> {

        return try {
            val edit = sharPref.edit()
            sharPref.all.map {
                edit.remove(it.key)
            }
            edit.apply()
            Result.success(true)
        }catch (failure: Exception){
            Result.failure(Failure.XmlError)
        }

    }

}