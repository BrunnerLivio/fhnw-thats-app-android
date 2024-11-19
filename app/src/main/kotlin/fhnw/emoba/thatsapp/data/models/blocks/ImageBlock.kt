package fhnw.emoba.thatsapp.data.models.blocks

import org.json.JSONObject

class ImageBlock(jsonObject: JSONObject): Block(jsonObject) {
    val url = jsonObject.getString("url")

    override fun asJSONString(): String {
        return """
            {
                "type": "${super.type.name}",
                "url": "$url"
            }
        """.trimIndent()
    }
}