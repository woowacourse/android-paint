package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path

class Circle(path: Path = Path(), paint: Paint) : Drawing(path, paint) {
    fun drawCircle(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ) {
        path.reset()
        path.addOval(left, top, right, bottom, Path.Direction.CW)
    }

    override fun updateColor(color: Int): Drawing {
        val paint = Paint(paint)
        paint.color = color
        return Circle(path, paint)
    }

    override fun updatePaintStyle(): Drawing {
        val paint = Paint(paint)
        paint.style = Paint.Style.FILL
        return Circle(path, paint)
    }
}
