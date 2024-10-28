package com.oasis.composebasic.repository

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepository(private val context: Context) {
    suspend fun load(url: String): Image? {
        Log.i("===", "load ")
        return withContext(Dispatchers.IO) {
            try {
                Thread.sleep(3000)
                val future = Glide.with(context).load(url)
                    .downloadOnly(200, 200)
                val imageBitmap =
                    BitmapFactory.decodeFile(future.get().absolutePath).asImageBitmap()
                Image(imageBitmap)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}

data class Image(val imageBitmap: ImageBitmap)

sealed class Results<out T>() {
    object Loading : Results<Image>()
    object Error : Results<Image>()
    data class Success(var image: Image?) : Results<Image>()
}