package fhnw.emoba.thatsapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.thatsapp.data.models.User

class LoginModel(context: ThatsAppModel) : ScreenModel(context) {
    var username by mutableStateOf("")

    override fun init() {

    }

    fun login() {
        val user = User("Hello", "avatar1", username)
        context.login(user)
    }
}