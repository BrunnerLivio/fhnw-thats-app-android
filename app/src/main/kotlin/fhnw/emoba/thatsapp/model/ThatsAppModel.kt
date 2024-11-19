package fhnw.emoba.thatsapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ThatsAppModel {
    var screenState by mutableStateOf(
        ScreenState(
            Screen.HOME,
            createScreenModel(Screen.HOME, this)
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


    fun init() {
        screenState.model.init()
    }
}