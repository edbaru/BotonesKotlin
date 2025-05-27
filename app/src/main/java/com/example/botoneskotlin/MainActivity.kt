package com.example.botoneskotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.botoneskotlin.ui.theme.BotonesKotlinTheme
import com.example.botoneskotlin.ui.view.DatoCuriosoViewModel
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.entryModelOf

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
            GraficoSimple()
            Carrusel()
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

@Composable
fun GraficoSimple() {
    var datos by remember { mutableStateOf(listOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f)) }

    val modelo = entryModelOf(*datos.toTypedArray())

    Column(
        modifier = Modifier.padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chart(
            chart = lineChart(),
            model = modelo
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {
            datos = List(6) { (1..10).random().toFloat() }
        }) {
            Text("Actualizar gráfico")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carrusel(){
    val items = listOf(
        R.drawable.sol,
        R.drawable.mercurio,
        R.drawable.venus,
        R.drawable.tierra,
        R.drawable.marte,
        R.drawable.jupiter,
        R.drawable.saturno,
        R.drawable.urano,
        R.drawable.neptuno
    )

    val carouselState = rememberCarouselState { items.size }

    HorizontalUncontainedCarousel(
        state = carouselState,
        itemWidth = 250.dp,
        itemSpacing = 12.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
        flingBehavior = CarouselDefaults.singleAdvanceFlingBehavior(carouselState),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) { index ->
        Image(
            painter = painterResource(id = items[index]),
            contentDescription = "Imagen $index",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BotonesKotlinTheme {
        GraficoSimple()
    }
}