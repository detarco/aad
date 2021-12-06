package com.detarco.add_playground.ut01.ex02

import android.content.Context
import com.detarco.add_playground.commons.serializer.GsonSerializer
import java.io.File

/**
 * Clase para persistir información en ficheros.
 */
class InvoiceFileLocalSource(
    private val context: Context,
    private val serializer: GsonSerializer
) {

    /**
     * Función que me permite guardar un cliente en un fichero.
     */
    fun save(invoice: InvoiceModel) {
        val file = getFile(INVOICE_FILENAME)
        file.appendText(
            serializer.toJson(invoice,InvoiceModel::class.java)+System.lineSeparator()
        )
    }

    /**
     * Función que me permite eliminar un cliente de un fichero.
     */
    fun remove(invoiceId: Int) {
        val file = getFile(getInvoiceDetailFileName(invoiceId))
        file.delete()
    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un fichero.
     */
    fun fetch(): List<InvoiceModel> {
        val invoices: MutableList<InvoiceModel> = mutableListOf()

        val file = getFile(INVOICE_FILENAME)

        val lines = file.readLines()

        lines.map{
                line ->
            val invoiceModel = serializer.fromJson(line, InvoiceModel::class.java)
            invoices.add(invoiceModel)
        }

        return invoices
    }

    fun findById(invoiceId: Int): InvoiceModel? {
        val file = getInvoiceDetailFileName(invoiceId)
        return if (file.isBlank()){
            null
        }else
            serializer.fromJson(file, InvoiceModel::class.java)
    }

    fun deleteFiles(){
        val file = getFile(INVOICE_FILENAME)
        file.delete()
    }

    private fun getFile(fileName: String): File{
        val file = File(context.cacheDir, fileName)

        if (!file.exists()){
            file.createNewFile()
        }

        return file

    }

    companion object{
        const val INVOICE_FILENAME: String = "aad_ut01_ex02_invoices.txt"
        fun getInvoiceDetailFileName(invoiceId: Int): String = "aad_invoice_detail_$invoiceId.txt"
    }
}