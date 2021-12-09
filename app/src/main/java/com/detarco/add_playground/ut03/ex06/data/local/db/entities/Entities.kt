package com.detarco.add_playground.ut03.ex06.data.local.db.entities

import androidx.room.*
import com.detarco.add_playground.ut03.ex06.domain.BarModel
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

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
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "urlMainPhoto") val urlMainPhoto: String,
    @ColumnInfo(name = "bar_id") val barId: String
){
    fun toModel(
        barEntity: BarEntity
    )= TapaModel (
        id,
        name,
        description,
        price,
        urlMainPhoto,
        barEntity.toModel()
    )

    companion object{
        fun toEntity(tapaModel:TapaModel, barId: String) =
            TapaEntity(
                tapaModel.id,
                tapaModel.name,
                tapaModel.description,
                tapaModel.price,
                tapaModel.urlMainPhoto,
                barId
            )
    }
}

@Entity(tableName = "competition")
data class CompetitionEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "start") val start: String,
    @ColumnInfo(name = "end") val end: String,
    @ColumnInfo(name = "tapas_id") val tapasId: String//List<TapaModel>
){
    fun toModel(
        tapasEntity: List<TapaEntity>
    )= CompetitionModel (
        id,
        start,
        end,
        tapasEntity.map { it.toModel() }.toMutableList()
    )
}

@Entity(
    tableName = "competition_tapa"
            primaryKeys = ["competition_id", "tapa_id]
)
data class CompetitionTapaEntity(
    @ColumnInfo(name = "competition_id") val competitionId : String,
    @ColumnInfo(name = "tapa_id") val tapaIds: String
){
    companion object{
        fun toEntity(competitionId: String, tapaIds: List<String>)=
            tapaIds.map{tapaId -> CompetitionTapaEntity(competitionId, tapaId)}
    }
}

data class BarAndTapa(
    @Embedded val barEntity: BarEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "bar_id"
    ) val tapaEntity: TapaEntity
)

data class TapaAndCompetition(
    @Embedded val competitionEntity: CompetitionEntity

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CompetitionTapaEntity::class,
            parentColumn = "competition_id",
            entityColumn = "tapa_id"
        )
    ) val tapaEntities: List<TapaEntity>
){
    fun toModel() = competitionEntity.toModel(tapaEntities)
}