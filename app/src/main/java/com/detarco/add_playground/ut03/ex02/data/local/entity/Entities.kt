package com.detarco.add_playground.ut03.ex02.data

import androidx.room.*
import com.detarco.add_playground.ut03.ex02.domain.CarModel
import com.detarco.add_playground.ut03.ex02.domain.JobModel
import com.detarco.add_playground.ut03.ex02.domain.PersonModel
import com.detarco.add_playground.ut03.ex02.domain.PetModel

@Entity(tableName = "person")
data class PersonEntity(
    //@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int
) {
    //Se puede obtener información de un fichero a través de esta función aparte del room
    /**
    fun toModel(petEntity: PetEntity, carsEntity: List<CarEntity>, jobEntities: List<JobEntity>) =
        PersonModel(
            id,
            name,
            age,
            null,
            petEntity.toModel(),
            carsEntity.map { carEntity -> carEntity.toModel() }.toMutableList(),
            jobEntities.map { jobEntity -> jobEntity.toModel() }.toMutableList()
        )
    */
    fun toModel(
        petEntity: PetEntity,
        carsEntity: List<CarEntity>,
        jobsEntity: List<JobEntity>
    ) =
        PersonModel(
            id,
            name,
            age,
            null,
            petEntity.toModel(),
            carsEntity.map { it.toModel() }.toMutableList(),
            jobsEntity.map { it.toModel() }.toMutableList()
        )

    companion object {
        fun toEntity(personModel: PersonModel) =
            PersonEntity(personModel.id, personModel.name, personModel.age)
    }
}

@Entity(tableName = "pet")
data class PetEntity(
    //@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "person_id") val personId: Int
) {
    fun toModel(): PetModel = PetModel(id, name, age)

    companion object {
        fun toEntity(petModel: PetModel, personId: Int) =
            PetEntity(petModel.id, petModel.name, petModel.age, personId)
    }
}

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "model") val name: String,
    @ColumnInfo(name = "person_id") val personId: Int
) {
    fun toModel(): CarModel = CarModel(id, brand, name)

    companion object {
        fun toEntity(carsModel: List<CarModel>, personId: Int) = carsModel.map {
            CarEntity(it.id, it.brand, it.model, personId)
        }
    }
}

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String
) {
    fun toModel(): JobModel = JobModel(id, name)

    companion object {
        fun toEntity(jobsModel: List<JobModel>) = jobsModel.map {
            JobEntity(it.id, it.name)
        }
    }
}

@Entity(
    tableName = "person_job",
    primaryKeys = ["person_id", "job_id"]
)
data class PersonJobEntity(
    @ColumnInfo(name = "person_id") val personId: Int,
    @ColumnInfo(name = "job_id") val jobId: Int
) {
    companion object {
        fun toEntity(personId: Int, jobIds: List<Int>) =
            jobIds.map{ jobId -> PersonJobEntity(personId, jobId)}
    }
}

data class PersonAndPet(
    //Tabla padre
    @Embedded val personEntity: PersonEntity,

    @Relation(
        parentColumn = "id",    //Atributo padre
        entityColumn = "person_id", //Crear atributo en la
    ) val petEntity: PetEntity,

    ) {
    fun toModel() = PersonModel(
        personEntity.id,
        personEntity.name,
        personEntity.age,
        "",
        PetModel(petEntity.id, petEntity.name, petEntity.age),
        mutableListOf(),
        mutableListOf()
    )
}

data class PersonAndPetAndCars(
    @Embedded val personEntity: PersonEntity,

    @Relation(
        parentColumn = "id",    //Atributo padre
        entityColumn = "person_id", //Crear atributo en la
    ) val petEntity: PetEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "person_id"
    ) val carEntities: List<CarEntity>
)

data class PersonAndPetAndCarsAndJobs(
    @Embedded val personEntity: PersonEntity,

    @Relation(
        parentColumn = "id",    //Atributo padre
        entityColumn = "person_id", //Crear atributo en la
    ) val petEntity: PetEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "person_id"
    ) val carEntities: List<CarEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PersonJobEntity::class,
            parentColumn = "person_id",
            entityColumn = "job_id"
        )
    ) val jobEntities: List<JobEntity>,
) {
    fun toModel() = personEntity.toModel(petEntity, carEntities, jobEntities)
}

