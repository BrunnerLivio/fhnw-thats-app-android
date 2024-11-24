package fhnw.emoba.thatsapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fhnw.emoba.thatsapp.data.models.User


@Composable
fun Avatar(user: User, height: Dp = 48.dp) {
    with(user) {
        Surface(
            modifier = Modifier.size(height),
            shape = RoundedCornerShape(50),
            color = MaterialTheme.colorScheme.primary

        ) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(name.first().toString())
            }
        }
    }
}