package fhnw.emoba.thatsapp.data.models

import fhnw.emoba.thatsapp.data.JSONSerializable
import fhnw.emoba.thatsapp.data.models.blocks.Block
import fhnw.emoba.thatsapp.data.models.blocks.TextBlock
import fhnw.emoba.thatsapp.data.map
import fhnw.emoba.thatsapp.data.models.blocks.ImageBlock
import fhnw.emoba.thatsapp.data.models.blocks.LocationBlock
import org.json.JSONObject

class Message(jsonObject: JSONObject) : JSONSerializable {
    val id = jsonObject.getString("id")
    val sender = jsonObject.getString("sender")
    val timestamp = jsonObject.getString("timestamp")
    val blocks = jsonObject.getJSONArray("blocks").map { createBlock(it) }.filterNotNull()

    private fun createBlock(jsonObject: JSONObject): Block? {
        val type = jsonObject.getString("type")
        return when (MessageType.valueOf(type)) {
            MessageType.TEXT -> TextBlock(jsonObject)
            MessageType.LOCATION -> LocationBlock(jsonObject)
            MessageType.IMAGE -> ImageBlock(jsonObject)
            else -> null
        }
    }

    override fun asJSONString(): String {
        return """
            {
                "id": "$id",
                "sender": "$sender",
                "timestamp": "$timestamp",
                "blocks": [${blocks.joinToString(",") { (it as JSONSerializable).asJSONString() }}]
            }
        """.trimIndent()
    }
}

