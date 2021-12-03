package com.detarco.add_playground.ut02.ex04.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.serializer.GsonSerializer
import com.detarco.add_playground.ut02.ex04.domain.CustomerModel

/**
 * Clase para persistir información en SharedPreferences Encriptado
 */
class CustomerSharPrefLocalSource(
    context: Context,
    private val serializer: GsonSerializer
    ) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private val encryptSharPref = EncryptedSharedPreferences(
        context,
        context.getString(R.string.preference_file_exercise04),
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /**
     * Función que me permite guardar un cliente en un SharedPreferences.
     */
    fun save(customer: CustomerModel) {
        val edit = encryptSharPref.edit()
        edit.putString(
            customer.id.toString(),
            serializer.toJson(customer, CustomerModel::class.java)
        )
        edit.apply()
    }

    /**
     * Función que me permite guardar un listado de clientes en un SharedPreferences.
     */
    fun save(customers: List<CustomerModel>) {
        clean()
        customers.map {
            customerModel ->
            save(customerModel)
        }
    }

    /**
     * Función que me permite modificar los datos de un cliente que se encuentran en un fichero.
     * Se puede modificar cualquier dato excepto el id del cliente.
     */
    fun update(customer: CustomerModel) {
        if (encryptSharPref.contains(customer.id.toString())){
            remove(customer.id)
        }
        encryptSharPref.edit()
            .putString(
                customer.id.toString(),
                serializer.toJson(customer, CustomerModel::class.java)
            )
            .apply()
    }

    /**
     * Función que me permite eliminar un cliente de un SharedPreferences.
     */
    fun remove(customerId: Int) {
        if (encryptSharPref.contains(customerId.toString())){
            val edit = encryptSharPref.edit()
            edit.remove(customerId.toString())
            edit.apply()
        }
    }

    private fun clean(){
        val edit = encryptSharPref.edit()
        encryptSharPref.all.map {
            edit.remove(it.key)
        }
        edit.apply()
    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un SharedPreferences.
     */
    fun fetch(): List<CustomerModel> {
        val customerList: MutableList<CustomerModel> = mutableListOf()
        encryptSharPref.all.map{
            customerList.add(it.value as CustomerModel)
        }
        return customerList
    }

    fun findById(customerId: Int): CustomerModel? {

        return encryptSharPref.getString(customerId.toString(), "example ")
            ?.let {
                serializer.fromJson(it, CustomerModel::class.java)
            }
    }

}