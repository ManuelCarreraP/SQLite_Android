# Aplicación SQLite para Android

## Descripción del Proyecto

Aplicación Android que demuestra el uso de SQLite para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) siguiendo la guía oficial de Android. La aplicación ejecuta automáticamente todas las operaciones de base de datos y muestra los resultados en Logcat.

## Objetivos Cumplidos

- **Crear una app usando SQLite**: Uso de `SQLiteOpenHelper` y operaciones CRUD
- **Utilizar Logcat para ver resultados**: Todos los resultados se muestran en Logcat con tag "SQLite"
- **Insertar datos**: Función `insertar()` que agrega nuevos usuarios
- **Actualizar datos**: Función `actualizar()` que modifica registros existentes  
- **Seleccionar datos**: Función `leerTodos()` que consulta y muestra todos los registros
- **Eliminar datos**: Función `eliminar()` que borra registros

## Archivos del Proyecto

### UsuarioContract.kt
Define las constantes para nombres de tablas y columnas siguiendo el patrón Contract recomendado en la guía oficial.

### DatabaseHelper.kt
Gestor de base de datos que extiende `SQLiteOpenHelper`. Implementa:
- `onCreate()`: Crea la tabla `usuarios` con columnas: `_id`, `nombre`, `edad`
- `insertar()`: Inserta nuevos usuarios usando `ContentValues`
- `leerTodos()`: Consulta y muestra todos los usuarios
- `actualizar()`: Modifica el nombre de un usuario por ID
- `eliminar()`: Elimina un usuario por ID

### MainActivity.kt
Actividad principal que ejecuta automáticamente todas las operaciones SQLite en el método `onCreate()`.

### AndroidManifest.xml
Configuración de la aplicación con la actividad principal como LAUNCHER.

## Operaciones SQLite Implementadas

### CREATE (Implícito)
```sql
        val sql = """
            CREATE TABLE ${UsuarioContract.TABLE_NAME} (
                ${UsuarioContract.COLUMN_ID} INTEGER PRIMARY KEY,
                ${UsuarioContract.COLUMN_NOMBRE} TEXT,
                ${UsuarioContract.COLUMN_EDAD} INTEGER
            )
        """.trimIndent()
```
### INSERT
```sql
        db.insertar("Ana", 28)
        db.insertar("Luis", 35)
```
### SELECT
```sql
        db.leerTodos()
```
### UPDATE
```sql
        db.actualizar(1, "Ana Actualizada")
```
### DELETE
```sql
        db.eliminar(2)
```

## Ver Resultados en Logcat
1. Abrir Logcat (View → Tool Windows → Logcat)

2. Filtrar por tag: "SQLite"

3. Ver todos los logs de operaciones

### Ejemplo de Salida en Logcat
```bash
=== DEMO SQLITE CON CONTRACT ===

1. INSERTANDO...
Insertado: Ana, 28 años
Insertado: Luis, 35 años

2. LEYENDO...
--- Datos ---
ID: 1, Nombre: Ana, Edad: 28
ID: 2, Nombre: Luis, Edad: 35
--- Fin ---

3. ACTUALIZANDO...
Actualizado ID 1: Ana Actualizada
--- Datos ---
ID: 1, Nombre: Ana Actualizada, Edad: 28
ID: 2, Nombre: Luis, Edad: 35
--- Fin ---

4. ELIMINANDO...
Eliminado ID 2
--- Datos ---
ID: 1, Nombre: Ana Actualizada, Edad: 28
--- Fin ---

Todas las operaciones SQLite completadas
Se usó Contract como indica la guía
```

## Conceptos Clave Implementados
1. Patrón Contract: Centraliza nombres de tablas y columnas

2. SQLiteOpenHelper: Gestiona creación y actualización de la BD

3. ContentValues: Usado para operaciones INSERT y UPDATE

4. Cursor: Recorre resultados de consultas SELECT

## Recursos y Referencias
- Guía oficial de SQLite en Android

- Documentación de SQLiteOpenHelper

- Patrón Contract para bases de datos
