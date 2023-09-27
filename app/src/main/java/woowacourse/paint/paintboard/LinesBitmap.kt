package woowacourse.paint.paintboard

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

class LinesBitmap(w: Int, h: Int, canvasPaint: Paint) {
    private val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    private val canvas = Canvas(bitmap).apply { drawPaint(canvasPaint) }
    fun create(lines: Lines): Bitmap {
        lines.forEach {
            canvas.drawPath(it.path, it.paint)
        }
        return bitmap
    }
}
