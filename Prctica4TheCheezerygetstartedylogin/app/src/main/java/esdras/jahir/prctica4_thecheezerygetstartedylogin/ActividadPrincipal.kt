package esdras.jahir.prctica4_thecheezerygetstartedylogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActividadPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad_principal)

        val button:Button = findViewById(R.id.button_get_start) as Button

        button.setOnClickListener{
            var intent: Intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}