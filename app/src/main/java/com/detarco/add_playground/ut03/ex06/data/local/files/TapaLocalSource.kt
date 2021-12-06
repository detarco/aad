package com.detarco.add_playground.ut03.ex06.data.local.files

import android.content.Context
import com.detarco.add_playground.commons.serializer.GsonSerializer
import com.detarco.add_playground.ut03.ex06.data.local.LocalDataSource
import com.detarco.add_playground.ut03.ex06.domain.Failure
import com.detarco.add_playground.ut03.ex06.domain.TapaModel
import java.io.File

/**
 * Clase para persistir información en ficheros.
 */
class TapaLocalSource(
    val context: Context,
    val serializer: GsonSerializer
    ): LocalDataSource {

    private lateinit var tapaFile: File

    override fun save(tapaModel: TapaModel): Result<Boolean> {
        return getTapas().mapCatching {
            val tapas = it.toMutableList()
            tapas.add(tapaModel)
            save(tapas)
            true
        }
    }

    fun update(tapa: TapaModel): Result<Boolean> {
        return getTapas().mapCatching {
            val tapas = it.toMutableList()
            val indexTapa = tapas.indexOfFirst { item -> item.id == tapa.id }
            if (indexTapa >= 0) {
                tapas[indexTapa] = tapa
            } else {
                tapas.add(tapa)
            }
            save(tapas)
            true
        }
    }

    fun remove(tapaId: String): Result<Boolean> {
        return getTapas().mapCatching {
            val tapas = it.toMutableList()
            val indexCustomer = tapas.indexOfFirst { item -> item.id == tapaId }
            if (indexCustomer >= 0) {
                tapas.removeAt(indexCustomer)
            }
            save(tapas)
            true
        }
    }

    /**
     * Recupera una tapa por su ID
     */
    override fun getTapaById(tapaId: String): Result<TapaModel> {
        return getTapas().mapCatching { it.first{ item -> item.id == tapaId } }
    }

    override fun getTapas(): Result<List<TapaModel>> {
        return try {
            getFile().mapCatching {
                val tapas: MutableList<TapaModel> = mutableListOf()
                val lines = it.readLines()
                lines.map { line ->
                    val tapaModel = serializer.fromJson(line, TapaModel::class.java)
                    tapas.add(tapaModel)
                }
                tapas
            }
        } catch (failure: Exception) {
            Result.failure(Failure.FileError)
        }
    }

    override fun save(tapaModels: List<TapaModel>): Result<Boolean> {
        return try {
            clearFile().mapCatching {
                tapaModels.map { tapaModel ->
                    tapaFile.appendText(serializer.toJson(tapaModel,
                        TapaModel::class.java) + System.lineSeparator())
                }
                true
            }
        } catch (failure: Exception) {
            Result.failure(Failure.FileError)
        }
    }

    private fun clearFile(): Result<Boolean> {
        return try {
            getFile().mapCatching {
                it.writeText("")
                true
            }
        } catch (failure: Exception) {
            Result.failure(Failure.FileError)
        }
    }

    fun deleteFiles(): Result<Boolean> {
        return try {
            getFile().mapCatching {
                it.delete()
                true
            }
        } catch (failure: Exception) {
            Result.failure(Failure.FileError)
        }
    }

    private fun buildFile(): Result<File> {
        return try {
            val file = File(context.filesDir, AAD_TAPA_FILENAME)
            if (!file.exists()) {
                file.createNewFile()
            }
            Result.success(file)
        } catch (ex: FileSystemException) {
            Result.failure(Failure.FileError)
        }
    }

    private fun getFile(): Result<File> {
        return try {
            if (!this::tapaFile.isInitialized) {
                buildFile()
            }
            Result.success(tapaFile)
        } catch (ex: Exception) {
            Result.failure(Failure.FileError)
        }
    }

    companion object {
        const val AAD_TAPA_FILENAME: String = "aad_ut03_ex06_tapa.txt"
    }
}