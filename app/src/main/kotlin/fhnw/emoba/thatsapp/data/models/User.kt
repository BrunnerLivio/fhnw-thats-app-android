package fhnw.emoba.thatsapp.data.models

import fhnw.emoba.thatsapp.data.JSONSerializable
import org.json.JSONObject

class User(
    val id: String,
    val greeting: String,
    val avatar: String,
    val name: String
) : JSONSerializable {
    constructor(
        jsonObject: JSONObject
    ) : this(
        jsonObject.getString("id"),
        jsonObject.getString("greeting"),
        jsonObject.optString("avatar"),
        jsonObject.optString("name")
    )


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

}