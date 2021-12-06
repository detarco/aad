# UT-01 Ejercicio 02

Desarrolla una aplicación que permita trabajar con ficheros. Queremos persistir información sobre
clientes y facturas de clientes. Una factura está asociada a un cliente. Una factura puede tener de
1 a N líneas de facturas. Cada línea de factura tendrá sólo un producto.

Para ello, debes implementar lo siguiente:

- La clase CustomerFileLocalSource se implementará en un fichero general (filesDir).
- La clase InvoiceFileLocalSource se implementará en un fichero caché (cacheDir)
- Una clase principal (Activity) que permita probar que todo funciona correctamente.
- Una función que calcule el total de una factura. Para ello, irá a una factura, recorrerá cada
  línea de factura que tenga e irá sumando el precio del producto. Lo mostrará por pantalla o Log.

Nota: La clase CustomerFileLocalSource y InvoiceFileLocalSource, y el fichero Models no pueden ser
modificadas.

## Documentación a entregar

- Hay que crear una rama llamada: ut01_ex02_files que salga de **develop**.
- Se creará una **Pull Request** con la solución propuesta añadiendo información útil.
- Me pondrá como revisor.

## Objetivo a conseguir

Desarrollar aplicaciones que gestionen información almacenada en ficheros.

## Criterios de evaluación

Los siguientes criterios permitiran saber si se ha conseguido el **objetivo**

- El alumno sabe crear ficheros.
- El alumno sabe eliminar ficheros.
- El alumno sabe actualizar la información en un fichero.
- El alumno sabe leer información de un fichero.
- El alumno sabe crear ficheros en la caché o de forma general.
- El alumno sabe cuando usar ficheros en caché o de forma general.

## Cosas a tener en cuenta

Se valorará positivamente:

- Elección de los nombres para las clases, funciones, variables.
- Uso de código optimizado en Kotlin.
- Que se pruebe a través de un Activity.
- Que se sigan los principios SOLID: responsabilidad única, abstracción de clases, etc.
