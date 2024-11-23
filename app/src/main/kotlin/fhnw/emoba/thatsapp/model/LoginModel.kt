package fhnw.emoba.thatsapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.thatsapp.data.models.User
import java.util.UUID

class LoginModel(context: ThatsAppModel) : ScreenModel(context) {
    var username by mutableStateOf("")

    override fun init() {

    }

    fun login() {
        val user = User(UUID.randomUUID().toString(), "Hello", "avatar1", username)
        context.login(user)
    }
}