package fhnw.emoba.thatsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fhnw.emoba.thatsapp.model.HomeModel
import fhnw.emoba.thatsapp.model.LoginModel
import fhnw.emoba.thatsapp.ui.components.TopBar

@Composable
fun LoginScreen(model: LoginModel) {
    Column {
        TopBar(model.context, content = { Text("AppSearchBar") })
        LoginBody(model)
    }
}

@Composable
fun LoginBody(model: LoginModel) {
    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Login", style = MaterialTheme.typography.headlineLarge)

        TextField(
            value = model.username,
            onValueChange = { model.username = it },
            label = { Text("Username") }
        )

        Button(
            onClick = { model.login() },
            enabled = model.username.isNotBlank()
        ) {
            Text("Login")
        }
    }
}