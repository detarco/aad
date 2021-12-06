package com.detarco.add_playground.ut03.ex01.data

import androidx.room.RoomDatabase
import androidx.room.Database

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    private fun demo(){

    }

}