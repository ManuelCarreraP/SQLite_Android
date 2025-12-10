package gz.dam.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    UsuarioContract.DATABASE_NAME,
    null,
    UsuarioContract.DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE ${UsuarioContract.TABLE_NAME} (
                ${UsuarioContract.COLUMN_ID} INTEGER PRIMARY KEY,
                ${UsuarioContract.COLUMN_NOMBRE} TEXT,
                ${UsuarioContract.COLUMN_EDAD} INTEGER
            )
        """.trimIndent()

        db.execSQL(sql)
        Log.d("SQLite", "Tabla ${UsuarioContract.TABLE_NAME} creada")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Simple
    }

    fun insertar(nombre: String, edad: Int) {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put(UsuarioContract.COLUMN_NOMBRE, nombre)
            put(UsuarioContract.COLUMN_EDAD, edad)
        }

        db.insert(UsuarioContract.TABLE_NAME, null, valores)
        db.close()
        Log.d("SQLite", "Insertado: $nombre, $edad años")
    }

    fun leerTodos() {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${UsuarioContract.TABLE_NAME}",
            null
        )

        Log.d("SQLite", "--- Datos ---")
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val nombre = cursor.getString(1)
            val edad = cursor.getInt(2)
            Log.d("SQLite", "ID: $id, Nombre: $nombre, Edad: $edad")
        }
        Log.d("SQLite", "--- Fin ---")

        cursor.close()
        db.close()
    }

    fun actualizar(id: Int, nuevoNombre: String) {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put(UsuarioContract.COLUMN_NOMBRE, nuevoNombre)
        }

        db.update(
            UsuarioContract.TABLE_NAME,
            valores,
            "${UsuarioContract.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
        Log.d("SQLite", "Actualizado ID $id: $nuevoNombre")
    }

    fun eliminar(id: Int) {
        val db = writableDatabase
        db.delete(
            UsuarioContract.TABLE_NAME,
            "${UsuarioContract.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
        Log.d("SQLite", "Eliminado ID $id")
    }
}