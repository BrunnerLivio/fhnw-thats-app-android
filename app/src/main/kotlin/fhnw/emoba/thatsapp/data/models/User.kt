package fhnw.emoba.thatsapp.data.models

import fhnw.emoba.thatsapp.data.JSONSerializable
import org.json.JSONObject

class User(jsonObject: JSONObject) : JSONSerializable {
    val id = jsonObject.getString("id")
    val greeting = jsonObject.getString("greeting")
    val avatar = jsonObject.optString("avatar")
    val name = jsonObject.optString("name")


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