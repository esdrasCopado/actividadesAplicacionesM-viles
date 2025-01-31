package esdras.jahir.asignacion4_calculadoraimc_copadomartinez

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ImcActivity : AppCompatActivity() {
    // Declaración de variables para la UI
    private lateinit var etKilos: EditText
    private lateinit var etEstatura: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView
    private lateinit var tvRange: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Forzar el tema antes de setContentView
        setTheme(R.style.Theme_Asignacion4_CalculadoraIMC_CopadoMartinez)
        setContentView(R.layout.activity_imc)

        // Relación de variables con la UI
        etKilos = findViewById(R.id.kilos) as EditText
        etEstatura = findViewById(R.id.estatura) as EditText
        btnCalcular = findViewById(R.id.btn_cal_imc) as Button
        tvResultado = findViewById(R.id.tv_resultado) as TextView
        tvRange = findViewById(R.id.tv_range) as TextView

        // Evento del botón
        btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }
    private fun calcularIMC() {
        val pesoStr = etKilos.text.toString()
        val estaturaStr = etEstatura.text.toString()

        // Validación de entrada
        if (pesoStr.isEmpty() || estaturaStr.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa peso y estatura", Toast.LENGTH_SHORT).show()
            return
        }

        val peso = pesoStr.toFloatOrNull()
        val estatura = estaturaStr.toFloatOrNull()

        if (peso == null || estatura == null || estatura <= 0) {
            Toast.makeText(this, "Datos inválidos, revisa los valores", Toast.LENGTH_SHORT).show()
            return
        }

        // Cálculo del IMC
        val imc = peso / (estatura * estatura)

        // Clasificación del IMC y cambio de color
        val (resultado, color) = when {
            imc < 18.5 -> "Bajo peso" to Color.BLUE
            imc < 24.9 -> "Peso normal" to Color.GREEN
            imc < 29.9 -> "Sobrepeso" to Color.YELLOW
            imc < 34.9 -> "Obesidad grado 1" to Color.rgb(255, 165, 0) // Naranja
            imc < 39.9 -> "Obesidad grado 2" to Color.RED
            else -> "Obesidad grado 3" to Color.rgb(139, 0, 0) // Rojo oscuro
        }

        // Mostrar resultado y cambiar color
        tvResultado.text = "IMC: %.2f".format(imc)
        tvRange.text = resultado
        tvRange.setTextColor(color)
    }
}