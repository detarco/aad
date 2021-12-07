package com.detarco.add_playground.ut03.ex06.data.local.db.entities

import androidx.room.*
import com.detarco.add_playground.ut03.ex06.domain.BarModel
import com.detarco.add_playground.ut03.ex06.domain.TapaModel
import java.sql.Date

@Entity(tableName = "bares")
data class BarEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String
){
    fun toModel(): BarModel{
        return BarModel(
            this.id,
            this.name,
            this.address
        )
    }
    companion object{
        fun toEntity(bar: BarModel): BarEntity {
            return BarEntity(
                bar.id,
                bar.name,
                bar.address
            )
        }
    }
}

@Entity(tableName = "tapas")
data class TapaEntity(
    @PrimaryKey @ColumnInfo(name = "id") val tapaId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "urlMainPhoto") val urlMainPhoto: String
){
    fun toModel(barEntity: BarEntity)= TapaModel (
        tapaId,
        name,
        description,
        price,
        urlMainPhoto,
        barEntity.toModel()
    )

    companion object{
        fun toEntity(tapaModel:TapaModel) = TapaEntity(
            tapaModel.id,
            tapaModel.name,
            tapaModel.description,
            tapaModel.price,
            tapaModel.urlMainPhoto
        )
    }
}

@Entity(tableName = "competition")
data class CompetitionEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "start") val start: Date,
    @ColumnInfo(name = "end") val end: Date
)

@Entity(
    tableName = "bar_tapa",
    primaryKeys = ["bar_id", "tapa_id"]
)
data class OneBarOneTapaEntity(
    @ColumnInfo(name = "bar_id") val barId: String,
    @ColumnInfo(name = "tapa_id") val tapaId: String
){
    companion object {
        fun toEntity(barId: String, tapaId:String) =
            OneBarOneTapaEntity(barId, tapaId)
    }
}

data class OneBarWithTapaCompetition(
    @Embedded val competitionEntity: CompetitionEntity,

    @Relation(
        parentColumn = "tapa_id",
        entityColumn = "id"
    ) val tapasEntities: List<OneBarOneTapaEntity>
)