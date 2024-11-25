package fhnw.emoba.thatsapp.model

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class Screen(val title: String) {
    HOME("Home"),
    LOGIN("Login"),
    CHAT("Chat")
}

abstract class ScreenModel(val context: ThatsAppModel) {
    abstract fun init()
    var isLoading by mutableStateOf(false)
}

fun createScreenModel(screen: Screen, ctx: ThatsAppModel, activity: ComponentActivity): ScreenModel {
    return when (screen) {
        Screen.HOME -> HomeModel(ctx)
        Screen.LOGIN -> LoginModel(ctx)
        Screen.CHAT -> ChatModel(ctx, activity)
    }
}

data class ScreenState(
    val screen: Screen,
    val model: ScreenModel
)