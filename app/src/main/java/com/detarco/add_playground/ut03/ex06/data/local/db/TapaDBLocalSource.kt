package com.detarco.add_playground.ut03.ex06.data.local.db

import android.content.Context
import android.util.Log
import com.detarco.add_playground.ut03.ex06.app.db.Ut03Ex06Database
import com.detarco.add_playground.ut03.ex06.data.local.TapaLocalSource
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.BarEntity
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.TapaEntity
import com.detarco.add_playground.ut03.ex06.domain.Failure
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

class TapaDBLocalSource(
    val context: Context
) : TapaLocalSource {

    private val db = Ut03Ex06Database.getInstance(context)
    private val TAG = TapaDBLocalSource::class.java.simpleName

    override fun getTapas(): Result<List<TapaModel>> {

        val tapas = db.tapaDao().findAllTapas()

        return try {
            Result.success(tapas.map {
                it.tapaEntity.toModel(it.barEntity)
            })
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    override fun getTapa(tapaId: String): Result<TapaModel> {
        return try {
            val tapa = db.tapaDao().findTapaByID(tapaId)
            Result.success(tapa.tapaEntity.toModel(tapa.barEntity))
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    override fun save(tapaModel: TapaModel): Result<Boolean> {
        return try {

            val tapaEntity = TapaEntity(
                tapaModel.id,
                tapaModel.name,
                tapaModel.description,
                tapaModel.price,
                tapaModel.urlMainPhoto,
                tapaModel.barModel.id
            )

            val barEntity = BarEntity(
                tapaModel.barModel.id,
                tapaModel.barModel.name,
                tapaModel.barModel.address
            )

            db.tapaDao()
                .saveTapa(
                    tapaEntity,
                    barEntity
                )

            Result.success(true)
        } catch (failure: Failure) {
            Result.failure(Failure.DbError)
        }
    }

    override fun save(tapaModels: List<TapaModel>): Result<Boolean> {
        return try {

            tapaModels.map { element ->
                save(element)
            }
            Result.success(true)
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    override fun updateTapa(tapaModel: TapaModel): Result<Boolean> {
        return try {

            val updateTapa = db.tapaDao()
                .findAllTapas()
                .first { item ->
                    item.tapaEntity.toModel(item.barEntity) == tapaModel
                }
            db.tapaDao()
                .updateTapa(updateTapa.tapaEntity)
            Log.d(TAG, "$updateTapa")
            Result.success(true)

        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }
    }

    override fun removeTapa(tapaId: String): Result<Boolean> {
        return try {

            val theTapa = db.tapaDao()
                .findAllTapas()
                .first { item ->
                    item.tapaEntity.id == tapaId
                }

            db.tapaDao().deleteTapa(theTapa.tapaEntity)
            Result.success(true)

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