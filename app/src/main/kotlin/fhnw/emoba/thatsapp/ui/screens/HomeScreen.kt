package fhnw.emoba.thatsapp.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fhnw.emoba.thatsapp.data.models.User
import fhnw.emoba.thatsapp.model.HomeModel
import fhnw.emoba.thatsapp.ui.components.Avatar
import fhnw.emoba.thatsapp.ui.components.TopBar

@Composable
fun HomeScreen(model: HomeModel) {
    Column {
        TopBar(model.context, content = { Text("AppSearchBar") })
        Body(model)
    }
}

@Composable
fun Body(model: HomeModel) {
    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Chats", style = MaterialTheme.typography.headlineLarge)
        UserList(model)
    }
}

@Composable
fun UserList(model: HomeModel) {
    with(model) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(context.users) { item ->
                UserItem(model, item)
            }
        }
    }
}

@Composable
fun UserItem(model: HomeModel, user: User) {
    with(user) {
        TextButton (onClick = { model.context.openChat(user) }) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Avatar(user, height = 48.dp)
                Column(Modifier.height(48.dp)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                        modifier = Modifier.height(47.dp)
                    )
                    HorizontalDivider()
                }
            }
        }

    }
}
