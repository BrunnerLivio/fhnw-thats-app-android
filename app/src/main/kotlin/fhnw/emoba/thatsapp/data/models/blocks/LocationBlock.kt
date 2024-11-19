package fhnw.emoba.thatsapp.data.models.blocks

import fhnw.emoba.thatsapp.data.models.GeoPosition
import org.json.JSONObject

class LocationBlock(jsonObject: JSONObject): Block(jsonObject) {
    val geoPosition = GeoPosition(jsonObject.getJSONObject("geoPosition"))

    override fun asJSONString(): String {
        return """
            {
                "type": "${super.type.name}",
                "geoPosition": ${geoPosition.asJSONString()}
            }
        """.trimIndent()
    }
}