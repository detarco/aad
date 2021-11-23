package com.detarco.add_playground.ut01.ex02

import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.commons.GsonSerializer
import java.io.File

/**
 * Clase para persistir información en ficheros.
 */
class InvoiceFileLocalSource(
    private val activity: AppCompatActivity,
    private val serializer: GsonSerializer<InvoiceModel>
) {

    /**
     * Función que me permite guardar un cliente en un fichero.
     */
    fun save(invoice: InvoiceModel) {
        val file = File(activity.cacheDir, "facturas.txt")
        file.writeText("$invoice")
    }

    /**
     * Función que me permite eliminar un cliente de un fichero.
     */
    fun remove(invoiceId: Int) {
        //TODO
    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un fichero.
     */
    fun fetch(): List<InvoiceModel> {
        //TODO
        return emptyList()
    }

    fun findById(invoiceId: Int): InvoiceModel? {
        //TODO
        return null
    }

    fun deleteFiles(){
        TODO()
    }
}