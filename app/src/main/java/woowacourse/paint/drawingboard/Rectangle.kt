package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path
import kotlin.math.max
import kotlin.math.min

class Rectangle(path: Path = Path(), paint: Paint) : Drawing(path, paint) {
    fun drawRect(
        startX: Float,
        startY: Float,
        pointX: Float,
        pointY: Float,
    ) {
        path.reset()
        path.addRect(
            min(startX, pointX),
            min(startY, pointY),
            max(startX, pointX),
            max(startY, pointY),
            Path.Direction.CW,
        )
    }

    override fun updateColor(color: Int): Drawing {
        val paint = Paint(paint)
        paint.color = color
        return Rectangle(path, paint)
    }

    override fun updatePaintStyle(): Drawing {
        val paint = Paint(paint)
        paint.style = Paint.Style.FILL
        return Rectangle(path, paint)
    }
}
