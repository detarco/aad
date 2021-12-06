package com.detarco.add_playground.ut02.ex04.data

import android.content.Context
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.serializer.GsonSerializer
import com.detarco.add_playground.ut02.ex04.domain.InvoiceModel

/**
 * Clase para persistir información en SharedPreferences.
 */
class InvoiceSharPrefLocalSource(
    context: Context,
    private val serializer: GsonSerializer
) {

    private val sharPref = context.getSharedPreferences(
        context.getString(R.string.preference_file_exercise04),
        Context.MODE_PRIVATE
    )

    /**
     * Función que me permite guardar un cliente en un sharedprefe.
     */
    fun save(invoiceModel: InvoiceModel) {
        val edit = sharPref.edit()
        edit.putString(invoiceModel.id.toString(), serializer.toJson(invoiceModel, InvoiceModel::class.java))
        edit.apply()
    }

    /**
     * Función que me permite eliminar un cliente de un SharedPreferences.
     */
    fun remove(invoiceId: Int) {
        if (sharPref.contains(invoiceId.toString())){
            val edit = sharPref.edit()
            edit.remove(invoiceId.toString())
            edit.apply()
        }
    }

    private fun clean(){
        val edit = sharPref.edit()
        sharPref.all.map {
            edit.remove(it.key)
        }
        edit.apply()
    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un SharedPreferences.
     */
    fun fetch(): List<InvoiceModel> {
        val invoiceList: MutableList<InvoiceModel> = mutableListOf()
        sharPref.all.map {
            invoiceList.add(it.value as InvoiceModel)
        }
        return invoiceList
    }

    fun findById(invoiceId: Int): InvoiceModel? {
        return sharPref.getString(invoiceId.toString(), "example ")
            ?.let {
                serializer.fromJson(it, InvoiceModel::class.java)
            }
    }
}