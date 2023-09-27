package woowacourse.paint.paintboard

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

class LinesBitmap(private val w: Int, private val h: Int, private val canvasPaint: Paint) {
    fun create(lines: Lines): Bitmap {
        val bitmap = getBitmap()
        val canvas = getCanvas(bitmap)
        lines.forEach {
            canvas.drawPath(it.path, it.paint)
        }
        return bitmap
    }

    private fun getBitmap(): Bitmap {
        return Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    }

    private fun getCanvas(bitmap: Bitmap): Canvas {
        return Canvas(bitmap).apply { drawPaint(canvasPaint) }
    }
}
