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
) : TapaLocalSource {

    private val sharPref = context.getSharedPreferences(
        context.getString(R.string.ut03_preference_file_exercise06),
        Context.MODE_PRIVATE
    )

    override fun save(tapaModel: TapaModel): Result<Boolean> {
        return try {
            val edit = sharPref.edit()
            edit.putString(tapaModel.id, serializer.toJson(tapaModel, TapaModel::class.java))
            edit.apply()
            Result.success(true)
        } catch (failure: Exception) {
            Result.failure(Failure.XmlError)
        }
    }

    override fun save(tapaModels: List<TapaModel>): Result<Boolean> {
        return try {
            sharPref.all.map {
                save(it.value as TapaModel)
            }
            Result.success(true)
        } catch (failure: Exception) {
            Result.failure(Failure.XmlError)
        }
    }

    override fun getTapas(): Result<List<TapaModel>> {
        return try {
            val tapaList: MutableList<TapaModel> = mutableListOf()
            sharPref.all.map {
                tapaList.add(it.value as TapaModel)
            }
            Result.success(tapaList)
        } catch (failure: Exception) {
            Result.failure(Failure.XmlError)
        }
    }

    override fun getTapa(tapaId: String): Result<TapaModel> {

        val tapa = sharPref.getString(tapaId, null)

        return if (tapa != null) {
            Result.success(serializer.fromJson(tapa, TapaModel::class.java))
        } else {
            Result.failure(Failure.XmlError)
        }

        //return getTapas().mapCatching { it.first { item -> item.id == tapaId } }

    }

    override fun updateTapa(tapaModel: TapaModel): Result<Boolean> {

        return try {
            if (sharPref.contains(tapaModel.id)) {
                removeTapa(tapaModel.id)
            }
            save(tapaModel)
        } catch (failure: Exception) {
            Result.failure(Failure.XmlError)
        }
    }

    override fun removeTapa(tapaId: String): Result<Boolean> {
        return try {
            if (sharPref.contains(tapaId)) {
                sharPref.edit()
                    .remove(tapaId)
                    .apply()
            }
            Result.success(true)
        } catch (failure: Exception) {
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
        } catch (failure: Exception) {
            Result.failure(Failure.XmlError)
        }

    }

}