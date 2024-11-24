package fhnw.emoba.thatsapp.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.thatsapp.data.Chat
import fhnw.emoba.thatsapp.data.ChatStore
import fhnw.emoba.thatsapp.data.connectors.MqttConnector
import fhnw.emoba.thatsapp.data.models.User

class ThatsAppModel {
    private val mqttConnector = MqttConnector()
    val chatStore = ChatStore(mqttConnector)

    var selectedChat by mutableStateOf<Chat?>(null)

    var screenState by mutableStateOf(
        ScreenState(
            Screen.LOGIN,
            createScreenModel(Screen.LOGIN, this)
        )
    )
        private set

    private val screenModels = mutableMapOf<Screen, ScreenModel>()

    fun navigateTo(screen: Screen) {
        val model =
            screenModels[screen] ?: createScreenModel(screen, this).also {
                screenModels[screen] = it
            }
        screenState = ScreenState(screen, model)
        screenState.model.init()
    }

    fun openChat(chat: Chat) {
        selectedChat = chat
        chat.markAsRead()
        navigateTo(Screen.CHAT)
    }

    fun login(user: User) {
        chatStore.login(user) {
            navigateTo(Screen.HOME)
        }
    }

    fun init() {
        screenState.model.init()
    }
}