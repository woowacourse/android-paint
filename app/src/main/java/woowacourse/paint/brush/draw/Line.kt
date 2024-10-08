package woowacourse.paint.brush.draw

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.brush.BrushType

data class Line(
    val brushType: BrushType = BrushType.PEN,
    val path: Path = Path(),
    override val paint: Paint = Paint(),
) : Draw {
    private var endX = DEFAULT_POINT
    private var endY = DEFAULT_POINT

    init {
        if (brushType == BrushType.ERASER) {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
    }

    fun move(
        x: Float,
        y: Float,
    ) {
        path.moveTo(x, y)
        endX = x
        endY = y
    }

    override fun drawing(
        x: Float,
        y: Float,
    ) {
        path.quadTo(endX, endY, calculateMidpoint(endX, x), calculateMidpoint(endY, y))
        endX = x
        endY = y
    }

    private fun calculateMidpoint(
        a: Float,
        b: Float,
    ): Float {
        return (a + b) / MID_POINT
    }

    companion object {
        private const val DEFAULT_POINT = 0f
        private const val MID_POINT = 2
    }
}
