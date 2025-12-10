package gz.dam.sqlite

import android.app.Activity
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("SQLite", "=== DEMO SQLITE CON CONTRACT ===")

        val db = DatabaseHelper(this)

        // 1. INSERT
        Log.d("SQLite", "\n1. INSERTANDO...")
        db.insertar("Ana", 28)
        db.insertar("Luis", 35)

        // 2. SELECT
        Log.d("SQLite", "\n2. LEYENDO...")
        db.leerTodos()

        // 3. UPDATE
        Log.d("SQLite", "\n3. ACTUALIZANDO...")
        db.actualizar(1, "Ana Actualizada")
        db.leerTodos()

        // 4. DELETE
        Log.d("SQLite", "\n4. ELIMINANDO...")
        db.eliminar(2)
        db.leerTodos()

    }
}