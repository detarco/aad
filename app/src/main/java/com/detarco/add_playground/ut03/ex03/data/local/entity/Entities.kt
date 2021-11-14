package com.detarco.add_playground.ut03.ex03.data.local.entity

import androidx.room.*
import com.detarco.add_playground.ut03.ex03.domain.CustomerModel
import com.detarco.add_playground.ut03.ex03.domain.ClothesModel


@Entity(tableName = "customer")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int
) {
    fun toModel(
        clothesEntity: List<ClothesEntity>
    ) =
        CustomerModel(
            id,
            name,
            age,
            clothesEntity.map { it.toModel() }.toMutableList()
        )

    companion object {
        fun toEntity(customerModel: CustomerModel) =
            CustomerEntity(customerModel.id, customerModel.name, customerModel.age)
    }
}

@Entity(tableName = "clothes")
data class ClothesEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "customer_id") val customerId: Int
) {
    fun toModel(): ClothesModel = ClothesModel(id, type)

    companion object {
        fun toEntity(clothesModel: List<ClothesModel>, customerId: Int) = clothesModel.map {
            ClothesEntity(it.id, it.type, customerId)
        }
    }
}

data class CustomerAndClothes(
    @Embedded val customerEntity: CustomerEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "customer_id"
    ) val clothesEntities: List<ClothesEntity>,

) {
    fun toModel() = customerEntity.toModel(clothesEntities)
}
