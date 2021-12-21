package com.detarco.add_playground.ut03.ex06.data.local.db.entities

import androidx.room.*
import com.detarco.add_playground.ut03.ex06.domain.BarModel
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

@Entity(tableName = "bares")
data class BarEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String
) {
    fun toModel(): BarModel {
        return BarModel(
            this.id,
            this.name,
            this.address
        )
    }

    companion object {
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
    @ColumnInfo(name = "barId") val barId: String
) {
    fun toModel(
        barEntity: BarEntity
    ) = TapaModel(
        id,
        name,
        description,
        price,
        urlMainPhoto,
        barEntity.toModel()
    )

    companion object {
        fun toEntity(tapaModel: TapaModel, barId: String) =
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

data class TapaAndBar(
    @Embedded val tapaEntity: TapaEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    ) val barEntity: BarEntity
) {
    fun toModel(): TapaModel =
        TapaModel(
            tapaEntity.id,
            tapaEntity.name,
            tapaEntity.description,
            tapaEntity.price,
            tapaEntity.urlMainPhoto,
            barEntity.toModel()
        )
}