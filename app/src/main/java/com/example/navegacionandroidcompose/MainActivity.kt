package com.example.navegacionandroidcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "inicio"
            ) {
                composable("inicio") {
                    PantallaInicio(navController)
                }

                composable("perfil/{nombre}") { backStackEntry ->
                    val nombre = backStackEntry.arguments?.getString("nombre")
                    PantallaPerfil(navController, nombre)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(navController: NavController) {
    var nombre by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pantalla Inicio")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(top = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Pantalla Inicio",
                modifier = Modifier
                    .padding(bottom = 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Ingrese su nombre") },
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )

            Button(
                onClick = {
                    navController.navigate("perfil/$nombre")
                }
            ) {
                Text("Ir al perfil")
            }
        }
    }
}

@Composable
fun PantallaPerfil(navController: NavController, nombre: String?) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Pantalla Perfil",
            modifier = Modifier.padding(bottom = 15.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Usuario: $nombre",
            modifier = Modifier
                .padding(bottom = 15.dp),
            fontSize = 20.sp
        )
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(
                text = "Volver",
                fontSize = 15.sp
            )
        }
    }
}