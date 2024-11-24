package fhnw.emoba.thatsapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.thatsapp.data.models.Message
import fhnw.emoba.thatsapp.data.models.blocks.TextBlock

class ChatModel(context: ThatsAppModel) : ScreenModel(context) {
    var messageText by mutableStateOf("")

    override fun init() {}

    fun leaveChat() {
        context.navigateTo(Screen.HOME)
    }

    fun sendMessage() {
        val textBlock = TextBlock(messageText)
        val newMessage = Message(context.chatStore.currentUser!!.id, arrayListOf(textBlock))
        context.chatStore.sendMessage(context.selectedChat!!.user, newMessage)
        messageText = ""
    }
}