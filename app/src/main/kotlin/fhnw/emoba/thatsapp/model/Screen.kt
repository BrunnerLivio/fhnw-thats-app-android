package fhnw.emoba.thatsapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class Screen(val title: String) {
    HOME ("Home"),
}

abstract class ScreenModel(val context: ThatsAppModel) {
    abstract fun init()
    var isLoading by mutableStateOf(false)
}

fun createScreenModel(screen: Screen, ctx: ThatsAppModel): ScreenModel {
    return when (screen) {
        Screen.HOME -> HomeModel(ctx)
    }
}

data class ScreenState(
    val screen: Screen,
    val model: ScreenModel
)