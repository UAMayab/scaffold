package org.guirao.scaffold

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.guirao.scaffold.ui.theme.ScaffoldTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.Int


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldTheme {
                // Our code already has a Scaffold component that we can use to wrap our content.
                // First, we define which components we want to use in the Scaffold.
                // In this case, we want to use the topBar, bottomBar, and floatingActionButton
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar() },
                    bottomBar = { BottomBar() },
                    floatingActionButton = { Fab() }
                ) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// For each component of the Scaffold, we define a separate composable function.
@Composable
private fun MainActivity.Fab() {
    // presses is the state of the button.
    var presses: Int by remember { mutableIntStateOf(0) }
    FloatingActionButton(onClick = {Log.d("FAB", presses++.toString())}) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainActivity.BottomBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 4.dp,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Mayan Doctor",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainActivity.TopBar() {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text("Mayan Doctor") },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Delete, contentDescription = "Localized description")
            };
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Email, contentDescription = "Localized description")
            };
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Localized description")
            };
        }
    )
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    Log.d("Mayan Doctor", "email: $email")
    Column(
        modifier = Modifier.padding(top = 90.dp)
    ) {
        Text(
            text = "We can reach you at: $email",
        );
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") }
        );

        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Password") }
        )
    }
}