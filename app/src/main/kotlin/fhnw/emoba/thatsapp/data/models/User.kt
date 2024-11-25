package fhnw.emoba.thatsapp.data.models

import android.graphics.Bitmap
import android.media.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import fhnw.emoba.thatsapp.data.JSONSerializable
import org.json.JSONObject
import java.util.UUID

class User(
    val id: UUID,
    val greeting: String,
    val avatar: String,
    val name: String
) : JSONSerializable {
    constructor(
        jsonObject: JSONObject
    ) : this(
        UUID.fromString(jsonObject.getString("id")),
        jsonObject.getString("greeting"),
        jsonObject.optString("avatar"),
        jsonObject.optString("name")
    )



    constructor(
        greeting: String,
        avatar: String,
        name: String,
    ) : this(UUID.randomUUID(), greeting, avatar, name)


    override fun asJSONString(): String {
        return """
            {
                "id": "$id",
                "greeting": "$greeting",
                "avatar": "$avatar",
                "name": "$name"
            }
        """.trimIndent()
    }


    var avatarImage by mutableStateOf<ImageBitmap?>(null)
}