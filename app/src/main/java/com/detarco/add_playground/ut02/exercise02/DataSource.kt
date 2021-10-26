package com.detarco.add_playground.ut02.exercise02

import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.commons.Serializer
import java.io.File

interface DataSource<T : LocalModel> {
    fun save(model: T)
    fun fetch(id: String): T?
}

class FileDataSource <T: LocalModel>(
    private val activity: AppCompatActivity,
    private val serializer: Serializer<T>
    ) : DataSource<T> {

    private val file = File(activity.filesDir, "aad_ex02.txt")

    override fun save(model: T) {
        file.writeText(serializer.toJson(model))
    }

    override fun fetch(id: String): T {
        val jsonModel: String = file.readText()
        return serializer.fromJson(jsonModel)
    }
}

class MemDataSource <T:LocalModel> : DataSource<T> {

    //private val DataStorage2 : MutableList<T> = mutableListOf()
    private val dataStorage = mutableListOf<T>()

    override fun save(model: T) {
        dataStorage.add(model)
    }

    override fun fetch(id: String): T? =

        //filter usado aquñi, simplificado por Android
        dataStorage.firstOrNull() { it.getId().toString() == id }

        /**
         *  dataStorage.forEach{
         *       if (it.getId().toString() == id){
         *    return it
         *    }
         *    }
         *    return null
         *    }
         */



}

class SharPrefDataSource<T: LocalModel>(private val activity: AppCompatActivity) : DataSource<T>{
    override fun save(model: T) {
        TODO("Not yet implemented")
    }

    override fun fetch(id: String): T {
        TODO("Not yet implemented")
    }

}