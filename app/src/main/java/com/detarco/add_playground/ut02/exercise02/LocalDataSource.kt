package com.detarco.add_playground.ut02.exercise02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.Serializer
import java.io.File

interface LocalStorage<T : LocalModel> {
    fun save(model: T)
    fun fetch(id: String): T?
}

class FileDataSource <T: LocalModel>(
    private val activity: AppCompatActivity,
    private val serializer: Serializer<T>
    ) : LocalStorage<T> {

    private val file = File(activity.filesDir, "aad_ex02.txt")

    override fun save(model: T) {
        file.writeText(serializer.toJson(model))
    }

    override fun fetch(id: String): T {
        val jsonModel: String = file.readText()
        return serializer.fromJson(jsonModel)
    }
}

class MemLocalStorage <T:LocalModel> : LocalStorage<T> {

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

class SharPrefDataSource<T: LocalModel>(
    private val activity: AppCompatActivity,
    private val serializer: Serializer<T>
    ) :
        /**Demo(),*/
        LocalStorage<T>{

    private val sharedPref = activity.getSharedPreferences(
        activity.getString(R.string.preference_file_exercise02),
        Context.MODE_PRIVATE
    )

    override fun save(model: T) {
        val edit = sharedPref.edit()
        edit?.putString(model.getId().toString(), serializer.toJson(model))
        edit?.apply()
    }

    override fun fetch(id: String): T? {
        val jsonModel = sharedPref.getString(id, "{}")
        return if (jsonModel != null){
            serializer.fromJson(jsonModel)
        }else{
            null
        }

    }

}

/**
 *   open class Demo(){
 *
 *   }
 */