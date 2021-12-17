package com.detarco.add_playground.ut03.ex06.data

import com.detarco.add_playground.ut03.ex06.data.local.TapaLocalSource
import com.detarco.add_playground.ut03.ex06.data.remote.RemoteDataSource
import com.detarco.add_playground.ut03.ex06.domain.TapaModel
import com.detarco.add_playground.ut03.ex06.domain.TapaRepository

class TapaDataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: TapaLocalSource
) : TapaRepository {

    /**
     * Obtiene un listado de tapas.
     * Se consulta en local, si existe un listado de tapas se devuelve, sino, se obtiene de remoto,
     * se guarda en local y se devuelve.
     */

    override fun fetchTapas(): Result<List<TapaModel>> {
        val resultTapas = localDataSource.getTapas()
        return if (resultTapas.isFailure) {
            remoteDataSource.getTapas().mapCatching {
                localDataSource.save(it)
                it
            }
        } else {
            resultTapas
        }

    }

    /**
     * Obtiene una tapa y se guarda en local
     * Se consulta en local, si existe la tapa se devuelve, sino, se obtiene de remoto,
     * se guarda en local y se devuelve.
     */
    override fun fetchTapa(tapaId: String): Result<TapaModel> {

        val tapa = localDataSource.getTapa(tapaId).getOrElse {
            return remoteDataSource.getTapa(tapaId).mapCatching {
                localDataSource.save(it)
                it
            }
        }
        return Result.success(tapa)
    }

}