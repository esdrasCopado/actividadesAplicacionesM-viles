package esdras.jahir.practica9.ui.theme.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun FormularioScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(Modifier.padding(20.dp))
        titulo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.padding(16.dp))
        formulario(Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun titulo(modifier: Modifier) {
    Text(
        text = "Datos del Usuario",
        style = TextStyle(fontSize = 34.sp),
        modifier = modifier
    )
}

@Composable
fun formulario(modifier: Modifier) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Campo para nombre
        nombreField(nombre, onNombreChange = { nombre = it })
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para apellidos
        apellidosField(apellidos, onApellidosChange = { apellidos = it })
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para edad
        edadField(edad, onEdadChange = { edad = it })
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar
        botonGuardar {
            guardarDatos(nombre, apellidos, edad)
        }
        EventoFireBase()
    }
}

@Composable
fun EventoFireBase() {
    var usuariosList by remember { mutableStateOf<List<User>>(emptyList()) }

    val userRef = FirebaseDatabase.getInstance().getReference("users")

    // Escuchar cambios en la base de datos
    LaunchedEffect(Unit) {
        userRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val userMap = snapshot.value as? Map<String, Any> // Obtener los datos como un mapa
                if (userMap != null) {
                    val nombre = userMap["nombre"] as? String
                    val apellidos = userMap["apellidos"] as? String
                    val edad = userMap["edad"] as? String
                    if (nombre != null && apellidos != null && edad != null) {
                        val usuario = User(nombre, apellidos, edad)
                        // Añadir el nuevo usuario a la lista
                        usuariosList = usuariosList + usuario
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val usuario = snapshot.getValue(User::class.java)
                if (usuario != null) {
                    // Actualizar el usuario en la lista, si es necesario
                    usuariosList = usuariosList.map { if (it.nombre == usuario.nombre) usuario else it }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val usuario = snapshot.getValue(User::class.java)
                if (usuario != null) {
                    // Eliminar el usuario de la lista
                    usuariosList = usuariosList.filter { it.nombre != usuario.nombre }
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Manejar el movimiento si es necesario
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error si es necesario
            }
        })
    }

    // Mostrar la lista de usuarios
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Usuarios desde Firebase",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista de usuarios
        LazyColumn {
            items(usuariosList) { usuario ->
                Text(
                    text = "Nombre: ${usuario.nombre}, Apellidos: ${usuario.apellidos}, Edad: ${usuario.edad}",
                    style = TextStyle(fontSize = 18.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


@Composable
fun nombreField(nombre: String, onNombreChange: (String) -> Unit) {
    TextField(
        value = nombre,
        onValueChange = { onNombreChange(it) },
        label = { Text("Nombre") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun apellidosField(apellidos: String, onApellidosChange: (String) -> Unit) {
    TextField(
        value = apellidos,
        onValueChange = { onApellidosChange(it) },
        label = { Text("Apellidos") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun edadField(edad: String, onEdadChange: (String) -> Unit) {
    TextField(
        value = edad,
        onValueChange = { onEdadChange(it) },
        label = { Text("Edad") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun botonGuardar(onSave: () -> Unit) {
    Button(
        onClick = { onSave() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Guardar")
    }
}
fun guardarDatos(nombre: String, apellidos: String, edad: String) {
    // Referencia a la base de datos de Firebase
    val database = FirebaseDatabase.getInstance()
    val userRef = database.getReference("users")

    // Crear un objeto User
    val user = User(nombre, apellidos, edad)

    // Guardar los datos en Firebase. Usamos un ID único para cada usuario.
    val userId = userRef.push().key // Genera un ID único
    if (userId != null) {
        userRef.child(userId).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Datos guardados correctamente")
            } else {
                println("Error al guardar los datos: ${task.exception}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FormularioScreen()
}
