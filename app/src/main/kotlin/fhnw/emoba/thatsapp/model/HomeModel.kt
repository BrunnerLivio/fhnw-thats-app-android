package fhnw.emoba.thatsapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.thatsapp.data.models.User

class HomeModel(context: ThatsAppModel) : ScreenModel(context) {
    var users = context.users

    override fun init() {

    }
}