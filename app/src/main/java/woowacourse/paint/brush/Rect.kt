package woowacourse.paint.brush

import android.content.Context
import android.graphics.Paint
import woowacourse.paint.CanvasPaint
import woowacourse.paint.ColorType

class Rect(
    override val paint: CanvasPaint,
    private val startXPoint: Float = INVALID_X,
    private val startYPoint: Float = INVALID_Y,
) : Brush() {
    init {
        paint.style = Paint.Style.FILL
    }

    override fun startDraw(
        pointX: Float,
        pointY: Float,
    ): Brush {
        return Rect(
            paint,
            pointX,
            pointY,
        ).apply { addRect(pointX, pointY, pointX, pointY, Direction.CW) }
    }

    override fun moveBrush(
        pointX: Float,
        pointY: Float,
    ) {
        reset()
        addRect(startXPoint, startYPoint, pointX, pointY, Direction.CW)
    }

    fun changeColor(
        colorType: ColorType,
        context: Context,
    ): Rect {
        return Rect(
            paint.changeColor(context.getColor(colorType.colorId)),
            startXPoint,
            startYPoint,
        )
    }

    companion object {
        private const val INVALID_X = -1f
        private const val INVALID_Y = -1f
    }
}
