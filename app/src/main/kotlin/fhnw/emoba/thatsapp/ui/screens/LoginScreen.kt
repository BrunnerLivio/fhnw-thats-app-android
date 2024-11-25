package fhnw.emoba.thatsapp.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fhnw.emoba.thatsapp.model.LoginModel
import fhnw.emoba.thatsapp.ui.components.TopBar

@Composable
fun LoginScreen(model: LoginModel) {
    Column {
        Scaffold(
            topBar = { TopBar(model.context, content = { Text("AppSearchBar") }) },
            content = { LoginBody(model, it) },
        )
    }
}

@Composable
private fun LoginBody(model: LoginModel, paddingValues: PaddingValues) {
    with(model) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Login", style = MaterialTheme.typography.headlineLarge)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Rounded with border box
                Surface(
                    modifier = Modifier
                        .clickable { takePhoto() }
                        .size(64.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(64.dp),
                ) {
                    if(photo == null) {
                        Icon(
                            Icons.Filled.CameraAlt,
                            "",
                            tint = Color.Black.copy(alpha = 0.15f),
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        Image(
                            bitmap = photo!!.asImageBitmap(),
                            contentDescription = ""
                        )
                    }


                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            "Take a Picture",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                TextField(
                    value = model.username,
                    onValueChange = { model.username = it },
                    label = { Text("Username") },
                    modifier = Modifier.weight(1F)
                )

            }


            Button(
                onClick = { model.login() },
                enabled = model.username.isNotBlank()
            ) {
                Text("Login")
            }
        }
    }

}
