package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import kotlin.math.pow
import kotlin.math.sqrt

data class Drawing(
    val path: Path = Path(),
    val paint: Paint = Paint(),
    var brush: Brush = Brush(),
) {
    init {
        paint.apply {
            isAntiAlias = true
        }
    }

    fun updateBrush(newBrush: Brush.() -> Brush) {
        brush = brush.update(newBrush)
        paint.apply {
            strokeWidth = brush.strokeWidth
            color = brush.color
            style =
                when (brush.brushType) {
                    BrushType.PENCIL -> Paint.Style.STROKE
                    BrushType.SQUARE -> Paint.Style.FILL
                    BrushType.CIRCLE -> Paint.Style.FILL
                    BrushType.ERASER -> Paint.Style.STROKE
                }
        }
    }

    fun move(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ) {
        when (brush.brushType) {
            BrushType.PENCIL -> path.lineTo(endX, endY)

            BrushType.SQUARE -> path.addRect(startX, startY, endX, endY, Path.Direction.CW)

            BrushType.CIRCLE -> {
                val radius =
                    sqrt(
                        (endX - startX).toDouble().pow(2.0) +
                            (endY - startY).toDouble().pow(2.0),
                    ).toFloat()
                path.addCircle(startX, startY, radius, Path.Direction.CW)
            }

            BrushType.ERASER -> path.lineTo(endX, endY)
        }
    }

    fun setEraseMode(erase: Boolean) {
        if (erase) {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        } else {
            paint.xfermode = null
        }
    }
}
