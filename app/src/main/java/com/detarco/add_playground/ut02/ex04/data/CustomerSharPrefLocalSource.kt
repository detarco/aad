package com.detarco.add_playground.ut02.ex04.data

import android.content.Context
import com.detarco.add_playground.ut02.ex04.domain.CustomerModel
import com.detarco.add_playground.commons.serializer.GsonSerializer

/**
 * Clase para persistir información en SharedPreferences Encriptado
 */
class CustomerSharPrefLocalSource(
    private val context: Context,
    private val serializer: GsonSerializer
    ) {

    private val nameXmlFile = "ut02_ex02_customers"
    private val sharedPref = context.getSharedPreferences(nameXmlFile, Context.MODE_PRIVATE)


    /**
     * Función que me permite guardar un cliente en un SharedPreferences.
     */
    fun save(customer: CustomerModel) {
        saveCustomerInXml(customer)
    }

    /**
     * Función que me permite guardar un listado de clientes en un SharedPreferences.
     */
    fun save(customers: List<CustomerModel>) {
        customers.map {
            customerModel ->
            saveCustomerInXml(customerModel)
        }
    }

    /**
     * Función que me permite modificar los datos de un cliente que se encuentran en un fichero.
     * Se puede modificar cualquier dato excepto el id del cliente.
     */
    fun update(customer: CustomerModel) {
        //TODO
    }

    /**
     * Función que me permite eliminar un cliente de un SharedPreferences.
     */
    fun remove(customerId: Int) {
        val edit = sharedPref.edit()
        edit?.clear()
        edit?.commit()
    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un SharedPreferences.
     */
    fun fetch(): List<CustomerModel> {
        val customers: MutableList<CustomerModel> = mutableListOf()

        customers.map {
            line->
            val customerModel = serializer.fromJson(line, CustomerModel::class.java)
        }
        fetchCustomerInXml()
        return emptyList()
    }

    fun findById(customerId: Int): CustomerModel? {

        return fetchCustomerInXml(customerId.toString(), CustomerModel::class.java)
    }

    private fun getCustomerXml(){

    }

    private fun saveCustomerInXml(customerModel: CustomerModel) {
        val edit = sharedPref.edit()
        edit?.putString(customerModel.id.toString() ,serializer.toJson(customerModel, CustomerModel::class.java))
        edit?.apply()
    }

    private fun fetchCustomerInXml(customerId: String, typeClass: Class<CustomerModel>): CustomerModel? {
        val jsonModel = sharedPref.getString(customerId, "{}")
        return if (jsonModel != null) {
            serializer.fromJson(jsonModel, typeClass)
        } else {
            null
        }
    }

}