package esdras.jahir.calculadorapractica

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class calculadoraActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private var currentInput = "" // Para almacenar la expresión
    private var lastNumeric = false // Controla si el último carácter ingresado es un número
    private var lastDot = false // Controla si ya se ingresó un punto decimal en el número actual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        textView = findViewById(R.id.textView)

        // Configurar eventos para los botones numéricos
        val numberButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9
        )
        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener { onNumberClick(it as Button) }
        }

        // Configurar eventos para los operadores
        val operatorButtons = listOf(
            R.id.buttonSuma, R.id.buttonRestar,
            R.id.buttonDividir
        )
        for (id in operatorButtons) {
            findViewById<Button>(id).setOnClickListener { onOperatorClick(it as Button) }
        }

        // Configurar otros botones
        findViewById<Button>(R.id.buttonPunto).setOnClickListener { onDecimalPointClick() }
        findViewById<Button>(R.id.buttonEliminar).setOnClickListener { onDeleteClick() }
        findViewById<Button>(R.id.buttonIgual).setOnClickListener { onEqualClick() }
    }

    // Manejo de los botones numéricos
    private fun onNumberClick(button: Button) {
        currentInput += button.text
        lastNumeric = true
        lastDot = false
        updateTextView()
    }

    // Manejo de los botones de operación
    private fun onOperatorClick(button: Button) {
        if (lastNumeric) { // Solo permitir operadores si el último caracter fue un número
            currentInput += " ${button.text} "
            lastNumeric = false
            lastDot = false
            updateTextView()
        }
    }

    // Manejo del botón de punto decimal
    private fun onDecimalPointClick() {
        if (lastNumeric && !lastDot) { // Solo permitir un punto por número
            currentInput += "."
            lastNumeric = false
            lastDot = true
            updateTextView()
        }
    }

    // Manejo del botón de borrar (DEL)
    private fun onDeleteClick() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1) // Borra el último caracter
            if (currentInput.isEmpty()) lastNumeric = false
            updateTextView()
        }
    }

    // Manejo del botón de igual
    private fun onEqualClick() {
        if (lastNumeric) { // Solo calcular si la última entrada fue un número
            try {
                val result = eval(currentInput)
                currentInput = result.toString()
                lastNumeric = true
                lastDot = false
                updateTextView()
            } catch (e: Exception) {
                textView.text = "Error"
            }
        }
    }

    // Método para actualizar el TextView con la expresión actual
    private fun updateTextView() {
        textView.text = currentInput
    }

    // Método para evaluar la expresión matemática
    private fun eval(expression: String): Double {
        return try {
            val tokens = expression.split(" ")
            var result = tokens[0].toDouble()
            var i = 1
            while (i < tokens.size) {
                val operator = tokens[i]
                val nextNumber = tokens[i + 1].toDouble()
                when (operator) {
                    "+" -> result += nextNumber
                    "-" -> result -= nextNumber
                    "÷" -> if (nextNumber != 0.0) result /= nextNumber else throw ArithmeticException("División por 0")
                }
                i += 2
            }
            result
        } catch (e: Exception) {
            0.0
        }
    }
}