package fhnw.emoba.thatsapp.data.models

import org.json.JSONObject
import org.junit.Test
import org.junit.Assert.assertEquals

class UserTest {

    @Test
    fun testUser() {
        val user = User(
            JSONObject(
                """
                    {
                        "id": "1",
                        "greeting": "Hello",
                        "avatar": "avatar.jpg",
                        "name": "John Doe"
                    }
                """.trimIndent()
            )
        )

        assertEquals("1", user.id)
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
                        "id": "1",
                        "greeting": "Hello",
                        "avatar": "avatar.jpg",
                        "name": "John Doe"
                    }
                """.trimIndent()
            )
        )

        val jsonString = user.asJSONString()
        val jsonObject = JSONObject(jsonString)

        assertEquals("1", jsonObject.getString("id"))
        assertEquals("Hello", jsonObject.getString("greeting"))
        assertEquals("avatar.jpg", jsonObject.getString("avatar"))
        assertEquals("John Doe", jsonObject.getString("name"))
    }
}