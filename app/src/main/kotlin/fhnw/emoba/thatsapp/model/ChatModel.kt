package fhnw.emoba.thatsapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.thatsapp.data.models.Message
import fhnw.emoba.thatsapp.data.models.User
import org.json.JSONObject

var messageList = listOf<Message>(
    Message(
        JSONObject(
            """
        {    "id": "1",    "sender": "1",    "timestamp": "2021-03-01T12:00:00Z",    "blocks": [        {            "type": "TEXT",            "text": "Hello World!"        },        {            "type": "IMAGE",            "url": "https://example.com/image.jpg"        },        {            "type": "LOCATION",            "geoPosition": {                "latitude": 47.123456,                "longitude": 8.123456,                "altitude": 500.0            }        }    ]}
    """.trimIndent()
        )
    ),
)

class ChatModel(context: ThatsAppModel) : ScreenModel(context) {
    lateinit var selectedUser: User
    var messages by mutableStateOf(messageList)
    var messageText by mutableStateOf("")

    override fun init() {
        if (context.selectedChat == null) {
            throw IllegalStateException("selectedChat is null")
        }
        selectedUser = context.selectedChat!!
    }

    fun leaveChat() {
        context.selectedChat = null
        context.navigateTo(Screen.HOME)
    }

    fun sendMessage() {
        val newMessage = Message(
            JSONObject(
                """
                {
                    "id": "${messageList.size + 1}",
                    "sender": "${context.user?.id}",
                    "timestamp": "2021-03-01T12:00:00Z",
                    "blocks": [
                        {
                            "type": "TEXT",
                            "text": "$messageText"
                        }
                    ]
                }
                """.trimIndent()
            )
        )
        messages += newMessage
        messageText = ""
    }
}