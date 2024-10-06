package woowacourse.paint.brush

import android.content.Context
import android.graphics.Paint.Style
import android.graphics.Path
import woowacourse.paint.CanvasPaint
import woowacourse.paint.ColorType

class Pen(override val paint: CanvasPaint) : Brush() {

    init {
        paint.style = Style.STROKE
    }


    override fun startDraw(pointX: Float, pointY: Float): Pen {
        return Pen(paint).apply {
            moveTo(pointX, pointY)
        }
    }

    override fun moveBrush(pointX: Float, pointY: Float) {
        lineTo(pointX, pointY)
    }

    fun changeBrushWidth(width: Float): Pen {
        return Pen(paint.changeBrushWidth(width))
    }

    fun changeColor(colorType: ColorType, context: Context): Pen {
        return Pen(paint.changeColor(context.getColor(colorType.colorId)))
    }


}