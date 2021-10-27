package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.vistas.NotaDetalle
import com.example.notesapp.vistas.VistaNotas

@Composable
fun Navigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            VistaNotas( navController = navController )
        }
        composable(route = Screen.DetailScreen.route){
            NotaDetalle( navController = navController )
        }
    }
}