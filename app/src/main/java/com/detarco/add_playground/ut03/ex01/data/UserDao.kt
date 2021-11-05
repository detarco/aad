package com.detarco.add_playground.ut03.ex01.data

import androidx.room.*

//Data access object
@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>

    //Todos aquellos usuarios cuyo id sea = 1
    @Query("SELECT * FROM user WHERE id= :id")
    fun findById(id: Int): UserEntity?

    //Todos los usuarios que estén en un listado de id
    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun findAllByIds(userIds: IntArray): List<UserEntity>

    //Que me devuelva todos los usuarios que el nombre sea igual a un
    //  parámetro y que solo devuelva un único valor
    @Query("SELECT * FROM user WHERE name LIKE :name AND :username LIMIT 1 ")
    fun findByName(name:String, username: String): UserEntity?

    @Insert
    fun insert(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

}