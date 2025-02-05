package esdras.jahir.prctica4_thecheezerygetstartedylogin

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val buttonColdDrinks: Button = findViewById(R.id.button_cold_drinks)
        buttonColdDrinks.text = getString(R.string.cold_drinks)

        val buttonHotDrinks: Button = findViewById(R.id.button_hot_drinks)
        buttonHotDrinks.text = getString(R.string.hot_drinks)

    }
}