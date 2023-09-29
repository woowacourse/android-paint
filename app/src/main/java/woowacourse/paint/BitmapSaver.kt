package woowacourse.paint

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.OutputStream

class BitmapSaver(private val context: Context) {
    fun save(bitmap: Bitmap, fileName: String): Result<Unit> {
        val contentValues = getContentValues(fileName)
        val contentResolver = context.contentResolver
        val imageUri: Uri? = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues,
        )
        imageUri?.let {
            val result = saveBitmap(bitmap, context, it)
            contentResolver.update(imageUri, contentValues, null, null)
            if (result) return Result.success(Unit)
        }
        return Result.failure(IllegalStateException("이미지 저장에 실패했습니다"))
    }

    private fun getContentValues(fileName: String): ContentValues {
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
            put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
    }

    private fun saveBitmap(bitmap: Bitmap, context: Context, imageUri: Uri): Boolean {
        val outputStream: OutputStream? = context.contentResolver.openOutputStream(imageUri)
        val result = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream?.close()
        return result
    }
}
