package woowacourse.paint.brush

import android.content.Context
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.CanvasPaint
import woowacourse.paint.ColorType

class Circle(
    override val paint: CanvasPaint,
    private val startXPoint: Float = INVALID_X,
    private val startYPoint: Float = INVALID_Y
) : Brush() {

    init {
        paint.style = Paint.Style.FILL
    }

    override fun startDraw(pointX: Float, pointY: Float): Brush {
        return Circle(
            paint, pointX, pointY
        ).apply { addOval(pointX, pointY, pointX, pointY, Direction.CW) }
    }

    override fun moveBrush(pointX: Float, pointY: Float) {
        reset()
        addOval(startXPoint, startYPoint, pointX, pointY, Direction.CW)
    }

    fun changeColor(colorType: ColorType, context: Context): Circle {
        return Circle(
            paint.changeColor(context.getColor(colorType.colorId)),
            startXPoint,
            startYPoint
        )
    }

    companion object {
        private const val INVALID_X = -1f
        private const val INVALID_Y = -1f
    }
}