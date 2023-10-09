package woowacourse.paint

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.OutputStream

class BitmapSaver(private val context: Context) {
    fun save(bitmap: Bitmap, fileName: String): Result<Unit> {
        val contentValues = getContentValues(fileName)
        val contentResolver = context.contentResolver
        val imageUri: Uri = getImageUri(contentResolver, contentValues).getOrElse {
            Log.e(TAG, it.message ?: "")
            return Result.failure(it)
        }
        saveBitmap(bitmap, context, imageUri).getOrElse {
            Log.e(TAG, it.message ?: "")
            return Result.failure(it)
        }
        contentResolver.update(imageUri, contentValues, null, null)
        return Result.success(Unit)
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

    private fun getImageUri(
        contentResolver: ContentResolver,
        contentValues: ContentValues,
    ): Result<Uri> {
        val imageUri: Uri = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues,
        ) ?: return Result.failure(IllegalStateException("이미지 uri를 가져오지 못했습니다"))
        return Result.success(imageUri)
    }

    private fun saveBitmap(bitmap: Bitmap, context: Context, imageUri: Uri): Result<Unit> {
        val outputStream: OutputStream? = context.contentResolver.openOutputStream(imageUri)
        val result = bitmap.compress(Bitmap.CompressFormat.JPEG, COMPRESS_RATE, outputStream)
        outputStream?.close()
        if (!result) return Result.failure(IllegalStateException("비트맵을 저장하지 못했습니다"))
        return Result.success(Unit)
    }

    companion object {
        private const val TAG = "BitmapSaver"
        private const val COMPRESS_RATE = 100
    }
}
