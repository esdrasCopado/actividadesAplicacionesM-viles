package esdras.jahir.guesstthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {

    var minValue = 0
    var maxValue = 100
    var num : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Asegúrate de tener el XML correcto

        val guessings: TextView = findViewById(R.id.guessing)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessted)

        generate.setOnClickListener{
            num = Random.nextInt(minValue,maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener{
            minValue = num
            if (checkingLimits()){
                num = Random.nextInt(minValue,maxValue)
                guessings.setText(num.toString())
            }
            guessings.setText("ganaste")

        }

        down.setOnClickListener{
            maxValue = num
            if (checkingLimits()){
                num = Random.nextInt(minValue,maxValue)
                guessings.setText(num.toString())
            }
            guessings.setText("ganaste")
        }

    }

    fun checkingLimits(): Boolean{
        return minValue !=maxValue
    }
}
