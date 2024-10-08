package com.example.proyectofinal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.proyectofinal.ui.theme.ContactAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "contactList") {
        composable("contactList") { ContactListScreen(navController) }
        composable("contactDetail") { ContactDetailScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contactos", fontWeight = FontWeight.Bold, fontSize = 22.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF388E3C))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("contactDetail") },
                containerColor = Color(0xFF388E3C)
            ) {
                Text("+", fontSize = 24.sp, color = Color.White)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            ContactGrid()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Contacto", fontWeight = FontWeight.Bold, ) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF81C784)),
                actions = {
                    Text(
                        text = "Guardar",
                        color = Color(0xFF4CAF50),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {  },
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Text(
                        text = "Salir",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navController.navigateUp() },
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.DarkGray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {

            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))) {
                Text("Agregar foto", color = Color.White, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))

            ContactTextField("Nombre")
            ContactTextField("Apellido")
            ContactTextField("Apodo")
            ContactTextField("Celular")
            ContactTextField("Correo")
            ContactTextField("Dirección")
        }
    }
}

@Composable
fun ContactTextField(label: String) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text(label, fontSize = 20.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text("Buscar", fontSize = 20.sp, color = Color.White) },
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            //.padding(8.dp)
    )
}

@Composable
fun ContactGrid() {
    val contacts = listOf(
        "Anthony Moreno", "Sergio Gonzales", "Sebastián Pérez",
        "Ronaldo Messi"
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in contacts.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ContactItem(contacts[i])
                if (i + 1 < contacts.size) {
                    ContactItem(contacts[i + 1])
                } else {
                    Spacer(modifier = Modifier.width(300.dp))
                }
            }
        }
    }
}

@Composable
fun ContactItem(name: String) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .width(180.dp)
            .height(230.dp)
            .background(Color(0xFFA5D6A7), shape = RoundedCornerShape(20.dp))
            .clickable {  },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(Color.Black, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 25.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}
