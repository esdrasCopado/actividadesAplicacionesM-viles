package Copado.Esdras.popcornfactory

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        // Buscar vistas manualmente
        val peliculaImg = findViewById<ImageView>(R.id.peliculaImagenDetalle)
        val peliculaNombre = findViewById<TextView>(R.id.peliculaTituloDetalle)
        val peliculaSinopsis = findViewById<TextView>(R.id.peliculaTextoDetalle)

        // Obtener datos del intent
        val bundle = intent.extras
        if (bundle != null) {
            peliculaImg.setImageResource(bundle.getInt("header"))
            peliculaNombre.text = bundle.getString("titulo", "Sin título") // Cambio aquí
            peliculaSinopsis.text = bundle.getString("sinopsis", "Sin descripción")
        }
    }
}
