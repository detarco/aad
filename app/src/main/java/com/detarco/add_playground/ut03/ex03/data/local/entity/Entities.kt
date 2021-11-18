package com.detarco.add_playground.ut03.ex03.data.local.entity

import androidx.room.*
import com.detarco.add_playground.ut03.ex03.data.local.LocalModel
import com.detarco.add_playground.ut03.ex03.domain.AlertModel
import com.detarco.add_playground.ut03.ex03.domain.FileModel

@Entity(tableName = "alerts")
data class AlertEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "datePublished") val datePublished: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "source") val source: String
) //: LocalModel {
    //override fun getId(): String = alertId
//}
{
    fun toModel(
        filesEntity : List<FilesEntity>
    ) =
        AlertModel(
            id,
            title,
            type,
            summary,
            datePublished,
            body,
            source,
            filesEntity.map { it.toModel() }.toMutableList()
        )
    companion object{
        fun toEntity(alertModel: AlertModel) =
            AlertEntity(
                alertModel.id,
                alertModel.title,
                alertModel.type,
                alertModel.summary,
                alertModel.datePublished,
                alertModel.body,
                alertModel.source
            )
    }
}

@Entity(tableName = "files")
data class FilesEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "alert_id") val alertId: String
) {
    fun toModel(): FileModel = FileModel(name, url)

    companion object {
        fun toEntity(filesModel: List<FileModel>, alertId: String) = filesModel.map {
            FilesEntity(it.name, it.url, alertId)
        }
    }
}

data class AlertAndFiles(
    @Embedded val alertEntity: AlertEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "alert_id"
    ) val filesEntity: List<FilesEntity>,

    ) {
    fun toModel() = alertEntity.toModel(filesEntity)
}