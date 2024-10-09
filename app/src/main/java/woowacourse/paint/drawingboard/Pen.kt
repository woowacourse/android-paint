package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path

class Pen(path: Path = Path(), paint: Paint = Paint()) : Drawing(path, paint) {
    fun moveTo(
        pointX: Float,
        pointY: Float,
    ) {
        path.moveTo(pointX, pointY)
    }

    fun lineTo(
        pointX: Float,
        pointY: Float,
    ) {
        path.lineTo(pointX, pointY)
    }

    override fun updateColor(color: Int): Drawing {
        val paint = Paint(paint)
        paint.color = color
        return Pen(path, paint)
    }

    override fun updatePaintStyle(): Drawing {
        val paint = Paint(paint)
        paint.style = Paint.Style.STROKE
        return Pen(path, paint)
    }
}
