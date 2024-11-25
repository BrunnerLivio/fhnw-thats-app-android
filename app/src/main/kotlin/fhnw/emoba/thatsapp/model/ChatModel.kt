package fhnw.emoba.thatsapp.model

import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import fhnw.emoba.thatsapp.data.models.GeoPosition
import fhnw.emoba.thatsapp.data.models.Message
import fhnw.emoba.thatsapp.data.models.blocks.LocationBlock
import fhnw.emoba.thatsapp.data.models.blocks.TextBlock
import android.Manifest
import android.util.Log
import androidx.compose.ui.platform.AndroidUriHandler

class ChatModel(context: ThatsAppModel, private val activity: ComponentActivity) :
    ScreenModel(context) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    var messageText by mutableStateOf("")

    override fun init() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        if (!hasLocationPermission()) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            sendLocation()
        }
    }

    fun sendLocation() {
        if (hasLocationPermission()) {
            try {
                fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
                    val location = task.result
                    if (location != null) {
                        val geoPosition =
                            GeoPosition(location.latitude, location.longitude, location.altitude)
                        val locationBlock = LocationBlock(geoPosition)
                        val newMessage =
                            Message(context.chatStore.currentUser!!.id, arrayListOf(locationBlock))
                        context.chatStore.sendMessage(context.selectedChat!!.user, newMessage)
                    } else {
                        // TODO: Handle null location
                    }
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
                // TODO: Handle null location
            }
        } else {
            requestLocationPermission()
        }
    }

    fun sendMessage() {
        val textBlock = TextBlock(messageText)
        val newMessage = Message(context.chatStore.currentUser!!.id, arrayListOf(textBlock))
        context.chatStore.sendMessage(context.selectedChat!!.user, newMessage)
        messageText = ""
    }

    fun showOnMap(position: GeoPosition) {
        Log.d("ChatModel", "Show on map: $position")
        AndroidUriHandler(activity).openUri(position.asOpenStreetMapsURL())
    }

    fun leaveChat() {
        context.navigateTo(Screen.HOME)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }
}