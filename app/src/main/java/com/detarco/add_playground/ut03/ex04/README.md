# UT-01 Ejercicio 02: Bases de datos ROOM

Desarrolla una aplicación que permita trabajar con Bases de Datos (Room). Queremos persistir
información sobre clientes, productos y facturas de clientes. Una factura está asociada a un
cliente. Una factura puede tener de 1 a N líneas de facturas. Cada línea de factura tendrá sólo un
producto.

**Puedes crear las clases necesarias en la capa de datos.**

Para ello, debes implementar una base de datos que permita lo siguiente:

- Crear las relaciones que sean necesarias para poder mantener relacionada la información de:
    - Customer.
    - Product.
    - Invoice.
    - InvoiceLine.
- Gestionar Clientes:
    - Añadir un cliente.
    - Modificar un cliente.
    - Eliminar un cliente.
    - Obtener un listado de clientes.
    - Obtener un único cliente a través de su ID.
- Gestionar Productos:
    - Añadir un producto.
    - Modificar un producto.
    - Eliminar un producto.
    - Obtener un listado de productos.
    - Obtener un único producto a través de su ID.
- Gestionar Facturas:
    - Añadir una factura con toda la información: Datos de la factuar, líneas de facturas.
    - Calcular el total de la factura. Se puede mostrar por LOG.
- La aplicación puede mostrar los resultados por LOG.
- Se pueden crear los objetos a mano y gestionarlos (añadir, eliminar, buscar, etc.)
  Ej:
  ```
    val producto = ProductModel(1, "Play Statios", "Model 6", 200.00 )
    db.productoDao.insert(producto)
  ```

## Documentación a entregar

- Hay que crear una rama llamada: ut03_ex04_db que salga de **develop**.
- Se creará una **Pull Request** con la solución propuesta añadiendo información útil.
- Me pondrá como revisor.

## Contextualización de los Objetivos a consequir

### Objetivo RA-2

- Desarrolla aplicaciones persistiendo la información en una base de datos.

### Objetivo RA-3

- Desarrolla aplicaciones usando Room: persistiendo y recuperando la información.

### Objetivo RA-4

- Desarrolla aplicaciones usando Room utilizando mecanismos de abstracción para desacoplar el código
  de Room en la capa de datos.

## Contextualización de los Criterios de Evaluación a seguir con los objetivos

Los siguientes criterios de evaluación permitiran saber si se han conseguido los **objetivos**
descritos en el apartado anterior.

### Criterios para el RA-2

- [ ] El alumno conoce las características de Room y sus conectores con Coroutines.
- [ ] El alumno sabe consultar la base de datos creada a través de App Inspection.
- [ ] El alumno sabe cuando almacenar información en un fichero, en SharedPreferences o base de
  datos.
- [ ] El alumno sabe crear una base de datos (Room) usando el patrón **Singleton**.
- [ ] El alumno sabe definir la estructura de una base de datos con Room: Dao, Entities, Relaciones.
- [ ] El alumno sabe desarrollar un DbLocalSource que gestione la información en Room: CRUD.
- [ ] El alumno sabe definir los Entities para cada una de las relaciones: 1:1, 1:N y N:M.
- [ ] El alumno sabe definir consultas personalizadas a través de DAO.
- [ ] El alumno sabe gestionar transacciones correctamente.
- [ ] El alumno sabe actualizar/migrar una base de datos.

### Criterios para el RA-3

- [ ] El alumno sabe incorporar ROOM al proyecto.
- [ ] El alumno sabe personalizar ROOM según las necesidades del proyecto: nombre de base de datos,
  versión, nombre de los campos, claves primarias, etc.
- [ ] El alumno sabe crear ficheros DAO y las entidades que crean las tablas.
- [ ] El alumno sabe persistir información a través de DAO.
- [ ] El alumno sabe modificar información a través de DAO.
- [ ] El alumno sabe consultar/recuperar información a través de DAO.
- [ ] El alumno sabe personalizar una función DAO con una consulta SQL.
- [ ] El alumno sabe gestionar transacciones con la anotación @Transaction en los DAO.

### Criterios para el RA-4

- [ ] El alumno sabe identificar las ventajas de usar Room con respecto a otros almacenamientos.
- [ ] El alumno sabe establecer los mecanismos para usar una fuente de datos con Room (
  DbLocalSource) que permita abstraerla del repository.
- [ ] El alumno sabe persistir modelos simples de dominio en Room.
- [ ] El alumno sabe persistir modelos relacionados de dominio en Room.
- [ ] El alumno sabe modificar modelos de dominio y actualizarlos en Room.
- [ ] El alumno sabe recuperar modelos de dominio a través de Room.
- [ ] El alumno sabe gestionar las transacciones y responder ante un fallo.
- [ ] El alumno sabe probar una aplicación con la capa de presentation, domain y data (repository y
  localsources).
- [ ] El alumno sabe documentar una **Pull Request** con el código realizado.

## Cosas a tener en cuenta

Se valorará positivamente:

- [ ] Elección de los nombres para las clases, funciones, variables.
- [ ] Uso de código optimizado en Kotlin.
- [ ] Que desarrolle una aplicación Android que pruebe el código realizado.
- [ ] Que se sigan los principios **SO**LI**D**:
    - [ ] Responsabilidad única (Single Responsability).
    - [ ] Principio Abierto/Cerrado (Principle Open/Close).
    - [ ] Uso de abstracciones en vez de concreciones.
- [ ] Código organizado con **Clean Architecture**: capa de presentation, domain, data.
- [ ] Organizar el código con Repository y LocalSource (abstracciones).
- [ ] Documentación y organización de la PR en Github: descripción, objetivos, capturas, etc.
- [ ] Uso de mappers para la conversión de los distintos modelos de datos.
- [ ] Los nombres propuestos están en inglés.