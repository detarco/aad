package com.detarco.add_playground.ut02.repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Interfaz genérica que define una fuente de datos sin especificar el tipo
 */

interface DataSource <T : Model> {
    fun save(model: List<T>)
    fun fetch(id: Int): T?
    fun fetchAll(): List<T>?

}



/**
 * Clase que permite almacenar objetos en memoria
 */
class MemDataSource<T : Model> : DataSource<T>{
    private val memDataStorage: MutableList<T> = mutableListOf()

    override fun save(model: List<T>) {
        memDataStorage.addAll(model)

//  Ejemplo de opción más larga
//        memDataStorage.forEach{
//            memDataStorage.add(it)
//        }

    }

    override fun fetch(id: Int): T? {
        memDataStorage.forEach {
            if (it.getId() == id) {
                return it
            }
        }
        return null
    }

    override fun fetchAll(): List<T>? = memDataStorage.toList()

}

/**
 * Clase que permite almacenar objetos en un fichero SharedPreferences
 */
class SharPrefDataSource<T : Model>(private val context: AppCompatActivity) : DataSource<T>{
    private val sharPrefDataStorage: MutableList<T> = mutableListOf()

    private val gson = Gson()
    private val type = object : TypeToken<UserModel>() {}.type

    private val sharedPref = context.getSharedPreferences(
        "ut02_shared_pref", Context.MODE_PRIVATE
    )

    override fun save(model: List<T>) {

        val edit = sharedPref.edit()

        model.forEach(){
            edit?.putString(it.getId().toString(), gson.toJson(it))
        }

        edit?.apply()

    }

    override fun fetch(id: Int): T? {
        //sharedPref.all.forEach(){}

        val allModels = fetchAll()
        allModels?.forEach{
            if (it.getId() == id ){
                return it
            }
        }
        return null
    }

    override fun fetchAll(): List<T>? {
        val allModels: MutableList<T> = mutableListOf()

        sharedPref.all?.values?.forEach{
            allModels.add(gson.fromJson(it as String, type))
        }
        return null
    }

}
