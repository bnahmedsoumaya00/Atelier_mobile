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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.tp03_homescreen.ui.theme.TP03_HomeScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_LONG).show()

        setContent {
            TP03_HomeScreenTheme {
                Home()
            }
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
        // Top: Clickable Logo
        Image(
            painter = painterResource(id = R.drawable.logo_isetk),
            contentDescription = "Logo ISET Kélibia",
            modifier = Modifier
                .size(128.dp)
                .clickable { showDialog = true }
        )

        // Center: Text
        Text(text = stringResource(R.string.center_text))

        // Bottom: Two Buttons Side by Side
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                Toast.makeText(context, "Button 1 clicked", Toast.LENGTH_SHORT).show()
            }) {
                Text(stringResource(R.string.button1))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                // You could show a Snackbar here if using Scaffold
                Toast.makeText(context, "Button 2 clicked", Toast.LENGTH_SHORT).show()
            }) {
                Text(stringResource(R.string.button2))
            }
        }
    }

    // Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.dialog_title)) },
            text = { Text(stringResource(R.string.dialog_text)) },
            confirmButton = {
                Button(onClick = {
                    Toast.makeText(context, "Confirmed!", Toast.LENGTH_SHORT).show()
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

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    TP03_HomeScreenTheme {
        Home()
    }
}