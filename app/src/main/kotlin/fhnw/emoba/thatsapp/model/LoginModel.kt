package fhnw.emoba.thatsapp.model

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.thatsapp.data.connectors.downloadBitmapFromFileIO
import fhnw.emoba.thatsapp.data.connectors.uploadBitmapToFileIO
import fhnw.emoba.thatsapp.data.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginModel(context: ThatsAppModel) : ScreenModel(context) {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    var username by mutableStateOf("")

    var uploadInProgress by mutableStateOf(false)

    var photo by mutableStateOf<Bitmap?>(null)

    var notificationMessage by mutableStateOf("")

    fun takePhoto() {
        context.cameraAppConnector.getBitmap(onSuccess = { photo = it },
            onCanceled = { notificationMessage = "Kein neues Bild" })
    }

    override fun init() {

    }

    private fun uploadToFileIO(onSuccess: (String) -> Unit) {
        uploadInProgress = true
        modelScope.launch {
            uploadBitmapToFileIO(bitmap = photo!!,
                onSuccess = {
                    onSuccess(it)
                },
                onError = { _, _ -> })  //todo: was machen wir denn nun?
            uploadInProgress = false
        }
    }

    fun login() {
        uploadToFileIO(onSuccess = { url ->
            val user = User("Hello", url, username)
            context.login(user)
            user.avatarImage = photo!!.asImageBitmap()
        })
    }
}