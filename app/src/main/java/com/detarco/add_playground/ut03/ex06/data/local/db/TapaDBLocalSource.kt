package com.detarco.add_playground.ut03.ex06.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex06.app.db.Ut03Ex06Database
import com.detarco.add_playground.ut03.ex06.data.local.TapaLocalSource
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.BarEntity
import com.detarco.add_playground.ut03.ex06.domain.Failure
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

class TapaDBLocalSource (
    val context: Context
    ) : TapaLocalSource{

    private val db = Ut03Ex06Database.getInstance(context)

    override fun getTapas(): Result<List<TapaModel>> {

        val tapas = db.tapaDao().findAllTapas()

        return try {
            tapas.mapCatching {
                it.map {
                    it.toModel(
                        /**
                         * Bar Entity problem
                         */
                        //BARENTITY???????????????
                    )
                }
                Result.success(it.map { it.toModel(barEntity = ) })
            }
        } catch (failure: Exception) {
            Result.failure(Failure.DbError)
        }

    }

    override fun getTapaById(tapaId: String): Result<TapaModel> {
        TODO("Not yet implemented")
    }

    override fun save(tapaModel: TapaModel): Result<Boolean> {
        val tapa =   db.tapaDao().saveTapa(tapaModel)
    }

    override fun save(tapaModels: List<TapaModel>): Result<Boolean> {
        TODO("Not yet implemented")
    }

}