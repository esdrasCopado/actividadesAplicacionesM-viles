package esdras.jahir.myfeelings.utilities

import android.content.Context
import android.util.Log
import java.io.IOException

class JSONFile {

    companion object {
        private const val MY_FEELINGS = "data.json"
    }

    fun saveData(context: Context, json: String) {
        try {
            context.openFileOutput(MY_FEELINGS, Context.MODE_PRIVATE).use { output ->
                output.write(json.toByteArray())
            }
            Log.d("JSONFile", "Datos guardados correctamente en $MY_FEELINGS")
        } catch (e: IOException) {
            Log.e("JSONFile", "Error al guardar los datos", e)
        }
    }

    fun getData(context: Context): String {
        val file = context.getFileStreamPath(MY_FEELINGS) // Verifica si el archivo existe
        if (!file.exists()) {
            Log.e("JSONFile", "Archivo data.json no encontrado, devolviendo vacío.")
            return ""  // Devuelve un string vacío en vez de lanzar error
        }

        return try {
            context.openFileInput(MY_FEELINGS).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            Log.e("JSONFile", "Error al leer los datos", e)
            ""
        }
    }

}

