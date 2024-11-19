package fhnw.emoba.thatsapp.data.models.blocks

import org.json.JSONObject

class TextBlock(jsonObject: JSONObject) : Block(jsonObject) {
    val text = jsonObject.getString("text")

    override fun asJSONString(): String {
        return """
            {
                "type": "${super.type.name}",
                "text": "$text"
            }
        """.trimIndent()
    }
}