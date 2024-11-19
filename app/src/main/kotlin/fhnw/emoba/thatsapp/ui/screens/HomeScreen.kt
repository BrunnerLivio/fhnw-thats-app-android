package fhnw.emoba.thatsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import fhnw.emoba.thatsapp.model.HomeModel
import fhnw.emoba.thatsapp.ui.components.TopBar

@Composable
fun HomeScreen(model: HomeModel) {

    Column {
        TopBar(model.context, content = { Text("AppSearchBar") })
        Text("HomeScreen")
    }
}