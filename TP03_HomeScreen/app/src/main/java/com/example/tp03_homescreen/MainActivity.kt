package com.example.tp03_homescreen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Toast au lancement (internationalisé)
        Toast.makeText(this, getString(R.string.toast_launch), Toast.LENGTH_LONG).show()

        setContent {
            Home()
        }
    }
}

@Composable
fun Home() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Logo cliquable → déclenche le dialogue
        Image(
            painter = painterResource(id = R.drawable.logo_isetk),
            contentDescription = stringResource(R.string.logo_content_desc),
            modifier = Modifier
                .size(128.dp)
                .clickable { showDialog = true }
        )

        // Texte central
        Text(text = stringResource(R.string.welcome_text))

        // Deux boutons côte à côte
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                Toast.makeText(context, context.getString(R.string.button1), Toast.LENGTH_SHORT).show()
            }) {
                Text(stringResource(R.string.button1))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                Toast.makeText(context, context.getString(R.string.button2), Toast.LENGTH_SHORT).show()
            }) {
                Text(stringResource(R.string.button2))
            }
        }
    }

    // AlertDialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.dialog_title)) },
            text = { Text(stringResource(R.string.dialog_message)) },
            confirmButton = {
                Button(onClick = {
                    Toast.makeText(context, context.getString(R.string.confirm), Toast.LENGTH_SHORT).show()
                    showDialog = false
                }) {
                    Text(stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}