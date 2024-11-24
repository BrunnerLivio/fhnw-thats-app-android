package fhnw.emoba.thatsapp.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.thatsapp.data.connectors.MqttConnector
import fhnw.emoba.thatsapp.data.models.User
import fhnw.emoba.thatsapp.data.services.MessageService
import fhnw.emoba.thatsapp.data.services.UserService
import java.util.UUID

val names = listOf(
    "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Heidi",
    "Ivan", "Judy", "Mallory", "Oscar", "Peggy", "Romeo", "Sybil", "Trent", "Victor", "Walter"
)

class ThatsAppModel {
    private val mqttConnector = MqttConnector()
    val messageService = MessageService(mqttConnector)
    val userService = UserService(mqttConnector)
    var user by mutableStateOf<User?>(null)
    var users by mutableStateOf(listOf<User>())
    var selectedChat by mutableStateOf<User?>(null)

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

    fun openChat(user: User) {
        selectedChat = user
        navigateTo(Screen.CHAT)
    }

    fun login(user: User) {
        this.user = user
        userService.connectWithUser(user, {
            it.onUserAdded { user ->
                users += user
            }
            it.subscribe()
            Log.d("ThatsAppModel", "init")
            navigateTo(Screen.HOME)
        })
    }

    fun init() {
        screenState.model.init()
    }
}