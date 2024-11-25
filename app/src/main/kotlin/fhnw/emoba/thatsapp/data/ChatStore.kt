package fhnw.emoba.thatsapp.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.thatsapp.data.connectors.MqttConnector
import fhnw.emoba.thatsapp.data.connectors.downloadBitmapFromFileIO
import fhnw.emoba.thatsapp.data.models.Message
import fhnw.emoba.thatsapp.data.models.MessageType
import fhnw.emoba.thatsapp.data.models.User
import fhnw.emoba.thatsapp.data.models.blocks.Block
import fhnw.emoba.thatsapp.data.models.blocks.TextBlock
import fhnw.emoba.thatsapp.data.services.MessageService
import fhnw.emoba.thatsapp.data.services.UserService
import java.util.UUID

class ChatStore(mqttConnector: MqttConnector) {
    private val userService = UserService(mqttConnector)
    private val messageService = MessageService(mqttConnector)

    var chats by mutableStateOf(listOf<Chat>())
    var currentUser by mutableStateOf<User?>(null)


    fun login(user: User, onLoggedIn: () -> Unit) {
        currentUser = user
        userService.connectWithUser(user, {
            it.onUserAdded { user ->
                Log.d("ThatsAppModel", "User added: ${user.name} - ${user.id}")
                addUser(user)
            }
            it.subscribe()
            Log.d("ThatsAppModel", "init")
            monitorMessages()
            onLoggedIn()
        })
    }

    private fun addUser(user: User) {
        chats += Chat(user)

        downloadBitmapFromFileIO(user.avatar, onSuccess = {
            user.avatarImage = it.asImageBitmap()
        },
            onError = {
                // We just log because we have the avatar as fallback
                // and showing an error message would be more confusing
                // than helping
                Log.d("ChatStore", "Error while downloading avatar")
            })
    }

    private fun monitorMessages() {
        messageService.subscribe(
            currentUser!!.id,
            onReceiveMessage = {
                getChatByUserId(it.sender)?.addMessage(it)
            },
        )
    }

    fun sendMessage(targetUser: User, message: Message) {
        messageService.publish(targetUser.id, message, onPublished = {
            val chat = getChatByUserId(targetUser.id)
            chat?.addMessage(message)
        }, onError = {
            Log.d("ChatStore", "Error while sending message")
        })
    }

    private fun getChatByUserId(userId: UUID): Chat? {
        return chats.find { it.user.id == userId }
    }
}


class Chat(val user: User) {
    val messages = mutableStateListOf<Message>();
    var mostRecentMessage by mutableStateOf<String>("")
        private set;
    var unreadMessages by mutableIntStateOf(0)
        private set;

    fun addMessage(message: Message) {
        messages.add(message)
        // Get last message block
        val block: Block = message.blocks.lastOrNull() ?: return
        mostRecentMessage = when (block.type) {
            MessageType.TEXT -> {
                (block as TextBlock).text
            }
            MessageType.LOCATION -> {
                "Location sent"
            }
            MessageType.IMAGE -> {
                "Image sent"
            }
            else -> {
                "New message"
            }
        }

        unreadMessages++
    }

    fun markAsRead() {
        unreadMessages = 0
    }
}