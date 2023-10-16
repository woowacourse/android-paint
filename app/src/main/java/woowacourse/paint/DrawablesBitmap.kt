package woowacourse.paint

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import woowacourse.paint.paintboard.drawables.BrushDrawables

class DrawablesBitmap(private val w: Int, private val h: Int, private val canvasPaint: Paint) {
    fun create(drawables: BrushDrawables): Bitmap {
        val bitmap = getBitmap()
        val canvas = getCanvas(bitmap)
        drawables.draw(canvas)
        return bitmap
    }

    private fun getBitmap(): Bitmap {
        return Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    }

    private fun getCanvas(bitmap: Bitmap): Canvas {
        return Canvas(bitmap).apply { drawPaint(canvasPaint) }
    }
}
