package fhnw.emoba.thatsapp.data.models

import org.json.JSONObject
import org.junit.Test
import org.junit.Assert.assertEquals
import java.util.UUID

class UserTest {

    @Test
    fun testUser() {
        val user = User(
            JSONObject(
                """
                    {
                        "id": "8585110c-8a94-4de0-8f86-63bb0302176b",
                        "greeting": "Hello",
                        "avatar": "avatar.jpg",
                        "name": "John Doe"
                    }
                """.trimIndent()
            )
        )

        assertEquals(UUID.fromString("8585110c-8a94-4de0-8f86-63bb0302176b"), user.id)
        assertEquals("Hello", user.greeting)
        assertEquals("avatar.jpg", user.avatar)
        assertEquals("John Doe", user.name)
    }

    @Test
    fun testUserToJSON() {
        val user = User(
            JSONObject(
                """
                    {
                        "id": "8585110c-8a94-4de0-8f86-63bb0302176b",
                        "greeting": "Hello",
                        "avatar": "avatar.jpg",
                        "name": "John Doe"
                    }
                """.trimIndent()
            )
        )

        val jsonString = user.asJSONString()
        val jsonObject = JSONObject(jsonString)

        assertEquals(UUID.fromString("8585110c-8a94-4de0-8f86-63bb0302176b"), user.id)
        assertEquals("Hello", jsonObject.getString("greeting"))
        assertEquals("avatar.jpg", jsonObject.getString("avatar"))
        assertEquals("John Doe", jsonObject.getString("name"))
    }
}