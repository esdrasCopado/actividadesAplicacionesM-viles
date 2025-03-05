package esdras.jahir.myfeelings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import esdras.jahir.myfeelings.utilities.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val jsonFile = JSONFile()
    private var veryHappy = 0.0F
    private var happy = 0.0F
    private var neutral = 0.0F
    private var sad = 0.0F
    private var verySad = 0.0F
    private var data = false
    private var lista = ArrayList<Emociones>()

    private lateinit var graphVeryHappy: View
    private lateinit var graphHappy: View
    private lateinit var graphNeutral: View
    private lateinit var graphSad: View
    private lateinit var graphVerySad: View
    private lateinit var graph: View
    private lateinit var icon: ImageView

    private lateinit var guardarButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        graph = findViewById(R.id.graph)
        graphVeryHappy = findViewById(R.id.graphVeryHappy)
        graphHappy = findViewById(R.id.graphHappy)
        graphNeutral = findViewById(R.id.graphNeutral)
        graphSad = findViewById(R.id.graphSad)
        graphVerySad = findViewById(R.id.graphVerySad)
        icon = findViewById(R.id.icon)

        guardarButton = findViewById(R.id.guardarButton)  // ✅ Agregar esta línea antes de usarlo

        val veryHappyButton: ImageButton = findViewById(R.id.veryHappyButton)
        val happyButton: ImageButton = findViewById(R.id.happyButton)
        val neutralButton: ImageButton = findViewById(R.id.neutralButton)
        val sadButton: ImageButton = findViewById(R.id.sadButton)
        val verySadButton: ImageButton = findViewById(R.id.verySadButton)

        fetchingData()

        if (!data) {
            actualizarGrafica()
        } else {
            actualizarGrafica()
            iconoMayoria()
        }

        // Configurar botones (después de inicializarlos correctamente)
        guardarButton.setOnClickListener { guardar() }
        veryHappyButton.setOnClickListener { incrementarEmocion("veryHappy") }
        happyButton.setOnClickListener { incrementarEmocion("happy") }
        neutralButton.setOnClickListener { incrementarEmocion("neutral") }
        sadButton.setOnClickListener { incrementarEmocion("sad") }
        verySadButton.setOnClickListener { incrementarEmocion("verySad") }
    }


    fun incrementarEmocion(emocion: String) {
        when (emocion) {
            "veryHappy" -> veryHappy++
            "happy" -> happy++
            "neutral" -> neutral++
            "sad" -> sad++
            "verySad" -> verySad++
        }
        iconoMayoria()
        actualizarGrafica()
    }




    fun fetchingData() {
        try {
            val json = jsonFile.getData(this).orEmpty()
            if (json.isNotEmpty()) {
                data = true
                try {
                    val jsonArray = JSONArray(json)
                    lista = parseJson(jsonArray)

                    for (i in lista) {
                        when (i.nombre) {
                            "Muy feliz" -> veryHappy = i.total
                            "Feliz" -> happy = i.total
                            "Neutral" -> neutral = i.total
                            "Triste" -> sad = i.total
                            "Muy triste" -> verySad = i.total
                        }
                    }
                } catch (e: Exception) {
                    Log.e("fetchingData", "Error al procesar el JSON", e)
                }
            }
        } catch (e: IOException) {
            Log.e("fetchingData", "Error al obtener datos del archivo JSON", e)
        }
    }


    fun iconoMayoria() {
        val maxEmotion = mapOf(
            "happy" to happy,
            "veryHappy" to veryHappy,
            "neutral" to neutral,
            "sad" to sad,
            "verySad" to verySad
        ).maxByOrNull { it.value }?.key

        val iconDrawable = when (maxEmotion) {
            "happy" -> R.drawable.sentiment_satisfied
            "veryHappy" -> R.drawable.sentiment_very_satisfied
            "neutral" -> R.drawable.sentiment_neutral
            "sad" -> R.drawable.sentiment_dissatisfied
            "verySad" -> R.drawable.sentiment_very_dissatisfied
            else -> R.drawable.sentiment_neutral  // Caso por defecto
        }

        // Usar ContextCompat para compatibilidad con versiones modernas de Android
        icon.setImageDrawable(ContextCompat.getDrawable(this, iconDrawable))
    }

    fun actualizarGrafica() {
        val total = veryHappy + happy + neutral + verySad + sad
        if (total == 0f) return  // Evitar división por cero

        val pVH = (veryHappy * 100f) / total
        val pH = (happy * 100f) / total
        val pN = (neutral * 100f) / total
        val pS = (sad * 100f) / total
        val pVS = (verySad * 100f) / total

        Log.d("porcentajes", "very_happy: $pVH")
        Log.d("porcentajes", "happy: $pH")
        Log.d("porcentajes", "neutral: $pN")
        Log.d("porcentajes", "sad: $pS")
        Log.d("porcentajes", "very_sad: $pVS")

        lista.clear()
        lista.add(Emociones("Muy feliz", pVH, R.color.mustard, veryHappy))
        lista.add(Emociones("Feliz", pH, R.color.orange, happy))
        lista.add(Emociones("Neutral", pN, R.color.greenie, neutral))
        lista.add(Emociones("Triste", pS, R.color.blue, sad))
        lista.add(Emociones("Muy triste", pVS, R.color.deepBlue, verySad))

        val fondo = CustomCircleDrawable(this, lista)

        graphVeryHappy.background = CustomBarDrawable(this, Emociones("Muy feliz", pVH, R.color.mustard, veryHappy))
        graphHappy.background = CustomBarDrawable(this, Emociones("Feliz", pH, R.color.orange, happy))
        graphNeutral.background = CustomBarDrawable(this, Emociones("Neutral", pN, R.color.greenie, neutral))
        graphSad.background = CustomBarDrawable(this, Emociones("Triste", pS, R.color.blue, sad))
        graphVerySad.background = CustomBarDrawable(this, Emociones("Muy triste", pVS, R.color.deepBlue, verySad))

        graph.setBackground(fondo)  // Se usa `setBackground` en vez de `background =`
    }

    fun parseJson(jsonArray: JSONArray): ArrayList<Emociones> {
        val lista = ArrayList<Emociones>()

        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)
                val nombre = jsonObject.getString("nombre")
                val porcentaje = jsonObject.getDouble("porcentaje").toFloat()
                val color = jsonObject.getInt("color")
                val total = jsonObject.getDouble("total").toFloat()

                lista.add(Emociones(nombre, porcentaje, color, total))
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
        }

        return lista
    }

    fun guardar() {
        val jsonArray = JSONArray()

        for (i in lista) {
            Log.d("guardar", i.toString())  // Se cambia el tag a "guardar"

            val j = JSONObject().apply {
                put("nombre", i.nombre)
                put("porcentaje", i.porcentaje)
                put("color", i.color)
                put("total", i.total)
            }
            jsonArray.put(j)  // No es necesario usar un contador manual
        }

        jsonFile?.saveData(this, jsonArray.toString())

        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
    }

}

