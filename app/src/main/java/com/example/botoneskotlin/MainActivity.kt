package com.example.botoneskotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.botoneskotlin.ui.theme.BotonesKotlinTheme
import com.example.botoneskotlin.ui.view.DatoCuriosoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BotonesKotlinTheme {
                Pantalla()
            }
        }
    }
}

@Composable
fun Pantalla(viewModel: DatoCuriosoViewModel = viewModel()) {
    val dato by viewModel.dato.collectAsState()

    Scaffold { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Contador()
            DatoCurioso(dato, viewModel)
        }
    }
}

@Composable
fun Contador() {
    var contador by remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("$contador")
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { contador++ }, modifier = Modifier.weight(5f)) {
                Text("Aumentar")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { contador-- }, modifier = Modifier.weight(5f)) {
                Text("Reducir")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DatoCurioso(dato: String, viewModel: DatoCuriosoViewModel) {
    Column (
        modifier = Modifier.padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dato curioso: $dato")
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { viewModel.fetchDato() }) {
            Text("Siguiente dato")
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BotonesKotlinTheme {
        Contador()
    }
}