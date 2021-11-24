package com.example.notesapp.vistas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.Screen
import com.example.notesapp.componentes.BarraNavegacion
import com.example.notesapp.datos.NotasDatabase
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.unit.sp
import com.example.notesapp.componentes.BarraNavegacionBottom

@Composable
fun VistaNotas (navController: NavController?, tipo : Int){

    val contex = LocalContext.current;
    val db = NotasDatabase.getDatabase(contex);
    var notas = db.notaDao().getTodos()
    
    if(tipo == 1){
        notas = db.notaDao().getTareas()
    }
    if(tipo == 2){
        notas = db.notaDao().getNotas()
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BarraNavegacion()
        LazyColumn(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ){
            items(notas){ nota ->
                Surface(modifier = Modifier.clickable {
                    var id = nota.id.toString()
                    navController?.navigate(Screen.EditarNota.withArgs(id))
                }) {
                    Text(text = nota.titulo, fontSize = 24.sp)
                }
            }
        }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        BarraNavegacionBottom(navController = navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                navController?.navigate(Screen.DetailScreen.route)
            },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.Black
        ) {
            Icon(Icons.Filled.Add,"")
        }

    }

}

@Composable
@Preview
fun PreviewVistaNotas(){
    VistaNotas(null,0)
}

