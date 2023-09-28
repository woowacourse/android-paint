package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Brush {
    private val path = Path()
    private val paint = Paint()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
    }

    fun drawPath(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    fun drawLine(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    fun movePoint(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    fun changeColor(color: Int) {
        paint.color = color
    }

    fun changeWidth(width: Float) {
        paint.strokeWidth = width
    }
}
