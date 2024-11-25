package fhnw.emoba.thatsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import fhnw.emoba.thatsapp.data.Chat
import fhnw.emoba.thatsapp.model.HomeModel
import fhnw.emoba.thatsapp.ui.components.Avatar
import fhnw.emoba.thatsapp.ui.components.TopBar

@Composable
fun HomeScreen(model: HomeModel) {
    Column {
        TopBar(model.context, content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth().padding(start=16.dp, end = 16.dp)
            ) {
                if (model.context.chatStore.currentUser != null) {
                    Avatar(model.context.chatStore.currentUser!!, 40.dp)
                }
            }

        })
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
            items(context.chatStore.chats) { item ->
                ChatItem(model, item)
            }
        }
    }
}

@Composable
fun ChatItem(model: HomeModel, chat: Chat) {
    with(chat) {
        TextButton(onClick = { model.context.openChat(chat) }) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Avatar(user, height = 48.dp)
                Column(Modifier.height(48.dp)) {
                    Box(Modifier.fillMaxHeight()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.weight(1F)) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                ) {
                                    Text(
                                        text = chat.user.name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = chat.mostRecentMessage,
                                        style = MaterialTheme.typography.bodySmall,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }


                            if (chat.unreadMessages > 0) {
                                Badge(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ) {
                                    Text("${chat.unreadMessages}")
                                }
                            }
                        }


                    }

                    HorizontalDivider()
                }
            }
        }
    }
}
