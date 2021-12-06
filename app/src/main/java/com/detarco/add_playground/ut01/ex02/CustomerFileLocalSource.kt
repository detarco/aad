package com.detarco.add_playground.ut01.ex02

import android.content.Context
import com.detarco.add_playground.commons.serializer.GsonSerializer
import java.io.File

/**
 * Clase para persistir información en ficheros.
 */
class CustomerFileLocalSource(
    private val context: Context,
    private val serializer: GsonSerializer
) {


    //private val file: Ut03Ex03ViewModel by lazy


    /**
     * Función que me permite guardar un cliente en un fichero.
     */
    fun save(customer: CustomerModel) {
        val file = getFile(CUSTOMERS_FILENAME)
        file.appendText(
            serializer.toJson(customer, CustomerModel::class.java)+System.lineSeparator()
        )
    }

    /**
     * Función que me permite guardar un listado de clientes en un fichero.
     */
    fun save(customers: List<CustomerModel>) {
        val file = getFile(CUSTOMERS_FILENAME)
        customers.map{
            customerModel ->
            file.appendText(
                serializer.toJson(customerModel,
                    CustomerModel::class.java
                ) + System.lineSeparator()
            )
        }
    }

    /**
     * Función que me permite modificar los datos de un cliente que se encuentran en un fichero.
     * Se puede modificar cualquier dato excepto el id del cliente.
     */
    fun update(customer: CustomerModel) {
        val file = getFile(getCustomerDetailFileName(customer.id))
        file.writeText(
            serializer.toJson(customer, CustomerModel::class.java)
        )
    }

    /**
     * Función que me permite eliminar un cliente de un fichero.
     */
    fun remove(customerId: Int) {
        val file = getFile(getCustomerDetailFileName(customerId))
        file.delete()
    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un fichero.
     */
    fun fetch(): List<CustomerModel> {

        val customers: MutableList<CustomerModel> = mutableListOf()

        val file = getFile(CUSTOMERS_FILENAME)

        val lines = file.readLines()

        lines.map{
                line ->
            val customerModel = serializer.fromJson(line, CustomerModel::class.java)
            customers.add(customerModel)
        }

        return customers
    }

    fun findById(customerId: Int): CustomerModel? {
        val file = getFile(getCustomerDetailFileName(customerId))

        return if (!file.exists()){
            null
        }else
            serializer.fromJson(file.readText(), CustomerModel::class.java)

        /**
         * val customers = fetch()
         * return customers.firstOrNull{item -> item.id == customerId}
         */

    }

    fun deleteFiles(){
        val file = getFile(CUSTOMERS_FILENAME)
        file.delete()
    }

    private fun getFile(fileName: String): File{
        val file = File(context.filesDir, fileName)

        if (!file.exists()){
            file.createNewFile()
        }
        return file

    }

    companion object{
        const val CUSTOMERS_FILENAME: String = "aad_ut01_ex02_customers.txt"
        fun getCustomerDetailFileName(customerId: Int): String = "aad_customer_detail_$customerId.txt"
    }
}