package fhnw.emoba.thatsapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.emoba.thatsapp.model.ThatsAppModel

@Composable
fun TopBar(
    model: ThatsAppModel,
    content: @Composable () -> Unit = {},
    surfaceColor: Color = MaterialTheme.colorScheme.background,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Surface(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(4.dp), color = surfaceColor
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                content()

            }
        }
    }
}