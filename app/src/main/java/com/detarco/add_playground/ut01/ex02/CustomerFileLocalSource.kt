package com.detarco.add_playground.ut01.ex02

import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.commons.serializer.GsonSerializer
import java.io.File

/**
 * Clase para persistir información en ficheros.
 */
class CustomerFileLocalSource(
    private val activity: AppCompatActivity,
    private val serializer: GsonSerializer
) {

    //private val customerList = mutableListOf<CustomerModel>()

    /**
     * Función que me permite guardar un cliente en un fichero.
     */
    fun save(customer: CustomerModel) {
        val file = File(activity.filesDir, "customers.txt")

        val custom = serializer.toJson(customer, CustomerModel::class.java)

        //file.appendText("$customer")
        file.appendText(custom)

    }

    /**
     * Función que me permite guardar un listado de clientes en un fichero.
     */
    fun save(customers: List<CustomerModel>) {
        val file = File(activity.filesDir, "customers.txt")

        customers.forEach {
            //val custom = serializer.toJson(it)
            //file.appendText(custom)
            file.appendText(serializer.toJson(it, CustomerModel::class.java))
        }
    }

    /**
     * Función que me permite modificar los datos de un cliente que se encuentran en un fichero.
     * Se puede modificar cualquier dato excepto el id del cliente.
     */
    fun update(customer: CustomerModel) {
        val file = File(activity.filesDir, "customers.txt")

        val custom = serializer.fromJson(file,)
    }

    /**
     * Función que me permite eliminar un cliente de un fichero.
     */
    fun remove(customerId: Int) {
        val file = File(activity.filesDir, "customers.txt")
        val lines = file.readLines()

    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un fichero.
     */
    fun fetch(): List<CustomerModel> {
        val file = File(activity.filesDir, "customers.txt")

        file.readText()

        return emptyList()
    }

    fun findById(customerId: Int): CustomerModel? {
        //TODO
        return null
    }

    fun deleteFiles(){
        TODO()
    }
}