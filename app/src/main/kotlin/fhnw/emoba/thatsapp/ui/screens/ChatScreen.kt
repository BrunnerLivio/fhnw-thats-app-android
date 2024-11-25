package fhnw.emoba.thatsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.emoba.thatsapp.data.models.Message
import fhnw.emoba.thatsapp.data.models.MessageType
import fhnw.emoba.thatsapp.data.models.blocks.Block
import fhnw.emoba.thatsapp.data.models.blocks.LocationBlock
import fhnw.emoba.thatsapp.data.models.blocks.TextBlock
import fhnw.emoba.thatsapp.model.ChatModel
import fhnw.emoba.thatsapp.model.HomeModel
import fhnw.emoba.thatsapp.model.LoginModel
import fhnw.emoba.thatsapp.ui.components.Avatar
import fhnw.emoba.thatsapp.ui.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(model: ChatModel) {
    with(model) {
        if (context.selectedChat == null) {
            return
        }
        Scaffold(
            topBar = {
                TopBar(model.context, content = {
                    IconButton(onClick = { model.leaveChat() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    Avatar(context.selectedChat!!.user, height = 32.dp)
                    Text(
                        text = context.selectedChat!!.user.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    )
                })
            },
            content = { paddingValues ->
                ChatBody(model, paddingValues)
            }
        )
    }

}

@Composable
fun ChatBody(model: ChatModel, paddingValues: PaddingValues) {
    with(model) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                ChatHistory(model)
            }
            MessageComposer(model)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageComposer(model: ChatModel) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    with(model) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp),

                ) {
                IconButton(onClick = { showBottomSheet = true }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    val minHeight = 32.dp
                    val maxHeight = 200.dp
                    val textLineHeight = 24.dp

                    val dynamicHeight = minHeight + (messageText.lines()
                        .count() * textLineHeight.value).coerceAtMost(maxHeight.value - minHeight.value).dp

                    TextField(
                        value = messageText,
                        onValueChange = { model.messageText = it },
                        modifier = Modifier
                            .height(dynamicHeight)
                            .fillMaxWidth(),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        shape = RoundedCornerShape(16.dp),
                        maxLines = 10, // Set max lines to allow multiline typing
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.hsl(0f, 0f, 1f, alpha = 0.5f),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.hsl(0f, 0f, 1f, alpha = 0.5f),
                        )
                    )
                }
                IconButton(onClick = {
                    sendMessage()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Send,
                        contentDescription = "Send Message"
                    )
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            FloatingActionButton(
                                onClick = {
                                    showBottomSheet = false
                                    sendLocation()

                                },
                                modifier = Modifier.size(64.dp),
                                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp),
                                shape = CircleShape,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location",
                                    modifier = Modifier.size(32.dp),
                                )
                            }
                            FloatingActionButton(
                                onClick = {},
                                modifier = Modifier.size(64.dp),
                                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp),
                                shape = CircleShape,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CameraAlt,
                                    contentDescription = "Camera",
                                    modifier = Modifier.size(32.dp),
                                )
                            }
                        }
                    }
                }

            }

        }

    }
}

@Composable
fun ChatHistory(model: ChatModel) {
    with(model) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(context.selectedChat!!.messages) { message ->
                ChatMessage(model, message)
            }
        }
    }
}

@Composable
fun ChatMessage(model: ChatModel, message: Message) {
    with(message) {
        // Check if the message sender is the current user
        val isCurrentUser = sender == model.context.chatStore.currentUser!!.id

        // Adjust alignment and color based on the sender
        val alignment = if (isCurrentUser) Alignment.End else Alignment.Start
        val backgroundColor = if (isCurrentUser) Color.Green else Color.Gray
        val textColor = if (isCurrentUser) Color.White else Color.Black

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
        ) {
            Surface(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .widthIn(max = 250.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    blocks.forEach { block ->
                        ChatBlock(model, block)
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBlock(model: ChatModel, block: Block) {
    with(block) {
        when (this.type) {
            MessageType.TEXT -> {
                TextBlock(model, this as TextBlock)
            }

            MessageType.IMAGE -> {
            }

            MessageType.LOCATION -> {
                LocationBlock(model, this as LocationBlock)
            }
        }
    }
}


@Composable
fun TextBlock(model: ChatModel, textBlock: TextBlock) {
    with(textBlock) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun LocationBlock(model: ChatModel, locationBlock: LocationBlock) {
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Location: ${locationBlock.geoPosition.dms()}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    model.showOnMap(locationBlock.geoPosition)
                },
        )
    }
}