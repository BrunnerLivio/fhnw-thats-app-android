package fhnw.emoba.thatsapp.model

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import java.util.Collections

object ImageLoader {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val imageCache =
        Collections.synchronizedMap(LRUCache<String, ImageBitmap>(40))


    fun loadImage(imagePath: String, fn: (imagePath: String) -> ImageBitmap): Deferred<ImageBitmap>{
        return modelScope.async {
            if (imageCache[imagePath] == null) {
                imageCache[imagePath] = fn(imagePath)
            }
            imageCache[imagePath]!!
        }
    }
}

class LRUCache<key, value>(val maxSize: Int) : LinkedHashMap<key, value>(maxSize, 0.75f, true) {
    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<key, value>?): Boolean {
        return size > maxSize
    }
}