package esdras.jahir.practica9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import esdras.jahir.practica9.ui.theme.Practica9Theme
import esdras.jahir.practica9.ui.theme.ui.FormularioScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            Practica9Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormularioScreen()
                }
            }
        }
    }
}

@Composable
fun ObtenerDatos(database: FirebaseDatabase) {

}






