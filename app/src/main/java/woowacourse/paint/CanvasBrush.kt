package woowacourse.paint

import android.content.Context
import android.graphics.Path

class CanvasBrush(
    val brush: BrushType,
    val paint: CanvasPaint,
    val startXPoint:Float = INVALID_X,
    val startYPoint:Float = INVALID_Y
) : Path() {

    init {
        if (brush == BrushType.ERASER) {
            require(paint.isEraseMode()) {
                "지우개를 선택하면, paint는 ERASE_MODE여야 합니다."
            }
        }
    }

    fun changeBrush(brush: BrushType): CanvasBrush {
        if (brush == BrushType.ERASER) {
           return CanvasBrush(brush, paint.changeToEraseMode())
        } else if (this.brush == BrushType.ERASER){
            return CanvasBrush(brush, paint.changeColorMode())
        }
        return CanvasBrush(brush, paint)
    }

    fun changeColor(color: ColorType, context: Context): CanvasBrush {
        return CanvasBrush(brush, paint.changeColor(context.getColor(color.colorId)))
    }

    fun changeBrushWidth(width: Float): CanvasBrush {
        return CanvasBrush(brush, paint.changeBrushWidth(width))
    }

    fun startDraw(pointX: Float, pointY: Float): CanvasBrush {

        val canvas = CanvasBrush(brush, paint, pointX, pointY)

        when (brush) {
            BrushType.PEN -> canvas.moveTo(
                pointX,
                pointY,
            )

            BrushType.RECT -> canvas.addRect(pointX, pointY, pointX, pointY, Direction.CW)
            BrushType.CIRCLE -> canvas.addOval(pointX, pointY, pointX, pointY, Direction.CW)
            BrushType.ERASER -> canvas.addOval(pointX, pointY, pointX, pointY, Direction.CW)
        }

        return canvas
    }

    fun moveBrush(pointX: Float, pointY: Float) {
        require(startXPoint != INVALID_X && startYPoint != INVALID_Y) {
            "startDraw()를 호출해야 brush를 움직일 수 있습니다."
        }

        when (brush) {
            BrushType.PEN -> lineTo(pointX, pointY)
            BrushType.RECT -> {
                this.reset()
                this.addRect(startXPoint, startYPoint, pointX, pointY, Direction.CW)
            }

            BrushType.CIRCLE -> {
                this.reset()
                this.addOval(startXPoint, startYPoint, pointX, pointY, Direction.CW)
            }

            BrushType.ERASER -> lineTo(pointX, pointY)
        }
    }

    companion object {
        private const val INVALID_X = -1f
        private const val INVALID_Y = -1f
    }
}