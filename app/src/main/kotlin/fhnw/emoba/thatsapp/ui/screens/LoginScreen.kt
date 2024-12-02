package fhnw.emoba.thatsapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import fhnw.emoba.thatsapp.model.LoginModel

@Composable
fun LoginScreen(model: LoginModel) {
    Scaffold(
        topBar = {
            Text(
                "ThatsApp",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .padding(64.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        content = { LoginBody(model, it) },
        containerColor = Color.Transparent,
        modifier = Modifier.zIndex(1F)
    )
    Canvas(modifier = Modifier.fillMaxSize()) {
        val colorStops = arrayOf(
            0f to Color(0xFF7976E3),
            0.9f to Color(0xFF312ED4),
            1f to Color(0xFF312ED4),
        )
        val brush = Brush.linearGradient(colorStops = colorStops)
        drawRect(
            brush = brush,
            size = size
        )
    }


}

@Composable
private fun LoginBody(model: LoginModel, paddingValues: PaddingValues) {
    with(model) {

        Surface(
            color = Color.White.copy(alpha = 0.5F),
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier.padding(top = 164.dp)
        ) {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Box(modifier = Modifier.padding(paddingValues)) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "Welcome back!",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 32.sp
                            ),
                        )
                        Text(
                            "Enter your details below",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(bottom = 32.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            Surface(
                                modifier = Modifier
                                    .clickable { takePhoto() }
                                    .size(64.dp),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(64.dp),
                            ) {
                                if (photo == null) {
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


                            OutlinedTextField(
                                value = model.username,
                                onValueChange = { model.username = it },
                                label = { Text("Username") },
                                modifier = Modifier.weight(1F),
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                )
                            )

                        }
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

    }
}


