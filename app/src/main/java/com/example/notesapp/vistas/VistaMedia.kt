package com.example.notesapp.vistas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.R
import com.example.notesapp.Screen
import com.example.notesapp.componentes.BarraNavegacion
import com.example.notesapp.componentes.EditarMediaDatos
import com.example.notesapp.datos.Media
import com.example.notesapp.datos.NotasDatabase
import androidx.compose.foundation.lazy.items
import androidx.core.content.FileProvider
import java.io.File

@Composable
fun VistaMedia(
    navController: NavController?,
    id: String?
) {

    // Estado
    var imageUrl by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    var imgSelected  by remember { mutableStateOf(false) }
    var idLong = 0L

    if(id != null){
        idLong = id.toLong()
    }

    val db = NotasDatabase.getDatabase(context);

    val medias = db.notaDao().getMedias(idLong)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUrl = uri
    }


    
    // Parte visual
    if(imgSelected == false) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BarraNavegacion(R.string.media_top)

            Surface(
                modifier = Modifier
                    .clickable {
                        //imageUrl = Uri.parse(media.url)
                        //var uri = Uri.parse(media.url)
                        //imageUrl = uriFoto
                        imgSelected = true
                    }
            ) {
                Text(text = imageUrl.toString())
            }

            LazyColumn(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
            ){



                items(medias){ media ->

                    val archivo = File(
                        context.getExternalFilesDir(null),
                        media.url
                    )
                    var uriFoto = FileProvider.getUriForFile(
                        context,
                        "com.example.notesapp.vistas",
                        archivo
                    )

                    Surface(
                        modifier = Modifier
                            .clickable {
                                //imageUrl = Uri.parse(media.url)
                                //var uri = Uri.parse(media.url)
                                //imageUrl = uriFoto
                                imgSelected = true
                            }
                    ) {
                        Text(text = media.url)
                    }
                }
            }


        }
    }
    else {
        var media = Media(idNota = idLong, url = imageUrl.toString(), descripcion = "Test")
        EditarMediaDatos(idTitulo = R.string.app_name, imageUrl = imageUrl!!, {agregarMedia(context,media)} )
    }
    
    // boton flotante
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                launcher.launch("image/*")
            },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.Black
        ) {
            Icon(Icons.Filled.Add,"")
        }

    }
}

fun agregarMedia (context: Context, media : Media){
    val db = NotasDatabase.getDatabase(context)
    db.notaDao().addMedia(media)
}
