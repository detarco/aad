package com.detarco.add_playground.ut03.ex06.data.local.db

import androidx.room.*
import com.detarco.add_playground.ut03.ex06.domain.BarModel
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

@Entity(tableName = "tapas")
data class TapaEntity(
    @PrimaryKey @ColumnInfo(name = "id") val tapaId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "urlMainPhoto") val urlMainPhoto: String,
    @ColumnInfo(name = "barId") val barName: String
){
    fun toModel(
        barEntity: BarEntity
    ) = TapaModel(
        tapaId,
        name,
        description,
        price,
        urlMainPhoto,
        barEntity.toModel()
    )
    companion object{
        fun toEntity(tapaModel: TapaModel, barName: String) =
            TapaEntity(
                tapaModel.id,
                tapaModel.name,
                tapaModel.description,
                tapaModel.price,
                tapaModel.urlMainPhoto,
                barName
            )
    }
}

@Entity(tableName = "bares")
data class BarEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String
){
    fun toModel(): BarModel = BarModel(id, name, address)

    companion object{
        fun toEntity(barModel: BarModel) =
            BarEntity(barModel.id, barModel.name, barModel.address)
    }
}

data class BarAndTapas(
    @Embedded val barEntity: BarEntity,

    @Relation(
        parentColumn = "name",
        entityColumn = "barName"
    ) val tapaEntity: TapaEntity,

){
    fun toModel() = BarModel(
        tapaEntity.tapaId,
        tapaEntity
    )
}