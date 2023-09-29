package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Line(
    private val path: Path = Path(),
    private val paint: Paint = Paint(),
) {

    init {
        initPaint()
    }

    private fun initPaint() {
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    fun lineTo(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }
}
