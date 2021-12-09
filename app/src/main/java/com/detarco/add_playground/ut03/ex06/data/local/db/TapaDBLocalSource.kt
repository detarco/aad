package com.detarco.add_playground.ut03.ex06.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex06.app.db.Ut03Ex06Database
import com.detarco.add_playground.ut03.ex06.data.local.TapaLocalSource
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.BarEntity
import com.detarco.add_playground.ut03.ex06.domain.BarModel
import com.detarco.add_playground.ut03.ex06.domain.Failure
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

class TapaDBLocalSource(
    val context: Context
) : TapaLocalSource {

    private val db = Ut03Ex06Database.getInstance(context)

    override fun getTapas(): Result<List<TapaModel>> {

        val tapas = db.tapaDao().findAllTapas()

        return try {
            Result.success(tapas.map {
                it.toModel(
                    BarEntity.toEntity(
                        BarModel("", "", "")
                    )
                )
            })
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    override fun getTapaById(tapaId: String): Result<TapaModel> {
        val tapa = db.tapaDao().findTapaByID(tapaId)

        return try {
            val tapaEntity = db
                .tapaDao()
                .findAllTapas().first { it -> it.id == tapaId }

            Result.success(tapaEntity.toModel(tapaEntity.))
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    override fun save(tapaModel: TapaModel): Result<Boolean> {
        return getTapas().mapCatching {
            db.tapaDao().saveTapa(tapaModel)
            true
        }
    }

    override fun save(tapaModels: List<TapaModel>): Result<Boolean> {
        return try {
            clearDataBase()
            tapaModels.map { element ->
                db.tapaDao().saveTapa(element)
            }
            Result.success(true)
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    override fun updateTapa(tapaModel: TapaModel): Result<Boolean> {
        return try {
            getTapas().mapCatching {

                val tapaList = it.toMutableList()
                val indexTapa = tapaList.indexOfFirst { item ->
                    item.id == tapaModel.id
                }
                if (indexTapa >= 0) {
                    tapaList[indexTapa] = tapaModel
                } else {
                    db.tapaDao().saveTapa(tapaModel)
                }
                db.tapaDao().saveTapa(tapaModel)
                true
            }
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    override fun removeTapa(tapaId: String): Result<Boolean> {
        return try {
            getTapas().mapCatching {
                val tapaList = it.toMutableList()
                val indexTapa = tapaList.indexOfFirst { item ->
                    item.id == tapaId
                }
                if (indexTapa >= 0) {
                    tapaList.removeAt(indexTapa)
                }
                save(tapaList)
                true
            }
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    private fun clearDataBase(): Result<Boolean> {
        return try {
            db.clearAllTables()
            Result.success(true)
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }


}