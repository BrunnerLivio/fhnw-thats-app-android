package fhnw.emoba.thatsapp.data.models

import fhnw.emoba.thatsapp.data.models.Message
import fhnw.emoba.thatsapp.data.models.blocks.ImageBlock
import fhnw.emoba.thatsapp.data.models.blocks.LocationBlock
import fhnw.emoba.thatsapp.data.models.blocks.TextBlock
import org.json.JSONObject
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class MessageTest {
    @Test
    fun testTextMessage() {
        val message = Message(
            JSONObject(
                """
                {
                    "id": "1",
                    "sender": "1",
                    "timestamp": "2021-03-01T12:00:00Z",
                    "blocks": [
                        {
                            "type": "TEXT",
                            "text": "Hello World!"
                        }
                    ]
                }
                """.trimIndent()
            )
        )


        assertEquals("1", message.id)
        assertEquals("1", message.sender)
        assertEquals("2021-03-01T12:00:00Z", message.timestamp)
        assertEquals(1, message.blocks.size)
        assertTrue(message.blocks[0] is TextBlock)
        assertEquals("Hello World!", (message.blocks[0] as TextBlock).text)
    }

    @Test
    fun testMultiMessage() {
        val message = Message(
            JSONObject(
                """
                {
                    "id": "1",
                    "sender": "1",
                    "timestamp": "2021-03-01T12:00:00Z",
                    "blocks": [
                        {
                            "type": "TEXT",
                            "text": "Hello World!"
                        },
                        {
                            "type": "IMAGE",
                            "url": "https://example.com/image.jpg"
                        },
                        {
                            "type": "LOCATION",
                            "geoPosition": {
                                "latitude": 47.123456,
                                "longitude": 8.123456,
                                "altitude": 500.0
                            }
                        }
                    ]
                }
                """.trimIndent()
            )
        )

        assertEquals("1", message.sender)
        assertEquals("2021-03-01T12:00:00Z", message.timestamp)
        assertEquals(3, message.blocks.size)
        assertTrue(message.blocks[0] is TextBlock)
        assertEquals("Hello World!", (message.blocks[0] as TextBlock).text)
        assertTrue(message.blocks[1] is ImageBlock)
        assertEquals("https://example.com/image.jpg", (message.blocks[1] as ImageBlock).url)
        assertTrue(message.blocks[2] is LocationBlock)
        val geoPosition = (message.blocks[2] as LocationBlock).geoPosition

        assertEquals(47.123456, geoPosition.latitude, 0.000001)
        assertEquals(8.123456, geoPosition.longitude, 0.000001)
        assertEquals(500.0, geoPosition.altitude, 0.000001)
    }

    @Test
    fun testToJSON() {
        val json = """
                {
                    "id": "1",
                    "sender": "1",
                    "timestamp": "2021-03-01T12:00:00Z",
                    "blocks": [
                        {
                            "type": "TEXT",
                            "text": "Hello World!"
                        },
                        {
                            "type": "IMAGE",
                            "url": "https://example.com/image.jpg"
                        },
                        {
                            "type": "LOCATION",
                            "geoPosition": {
                                "latitude": 47.123456,
                                "longitude": 8.123456,
                                "altitude": 500.0
                            }
                        }
                    ]
                }
                """.trimIndent()

        val message = Message(JSONObject(json))

        // Used ChatGPT for this: Assert equals and whitespace and newlines are ignored
        assertEquals(JSONObject(json).toString(), JSONObject(message.asJSONString()).toString())
    }

}