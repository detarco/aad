package com.detarco.add_playground.ut02.ex04.data

import com.detarco.add_playground.ut02.ex04.domain.InvoiceModel

/**
 * Clase para persistir información en SharedPreferences.
 */
class InvoiceSharPrefLocalSource {

    /**
     * Función que me permite guardar un cliente en un sharedprefe.
     */
    fun save(invoice: InvoiceModel) {
        //TODO
    }

    /**
     * Función que me permite eliminar un cliente de un SharedPreferences.
     */
    fun remove(invoiceId: Int) {

    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un SharedPreferences.
     */
    fun fetch(): List<InvoiceModel> {
        //TODO
        return emptyList()
    }

    fun findById(invoiceId: Int): InvoiceModel? {
        //TODO
        return null
    }
}