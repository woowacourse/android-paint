package woowacourse.paint.brush.draw

import android.graphics.Paint
import android.graphics.RectF
import woowacourse.paint.brush.Brush
import woowacourse.paint.brush.BrushType
import woowacourse.paint.brush.ColorPalette

class Drawn {
    private var brush: Brush = Brush()
    private var currentDraw: Draw = Line()
    val draws: MutableList<Draw> = mutableListOf()

    fun getBrushColorRes(): Int {
        return brush.colorPalette.colorRes
    }

    fun getBrushWidth(): Float {
        return brush.width
    }

    fun startDraw(
        x: Float,
        y: Float,
        paint: Paint,
    ) {
        setDraw(x, y, paint)
        draws.add(currentDraw)
    }

    fun drawing(
        x: Float,
        y: Float,
    ) {
        currentDraw.drawing(x, y)
    }

    fun changeBrushType(
        brushType: BrushType,
        paint: Paint,
    ) {
        brush = brush.changeType(brushType)
        setDraw(paint = paint)
    }

    fun changeLineWidth(
        width: Float,
        paint: Paint,
    ) {
        brush = brush.changeWidth(width)
        setDraw(paint = paint)
    }

    fun changeColor(
        colorPalette: ColorPalette,
        paint: Paint,
    ) {
        brush = brush.changeColor(colorPalette)
        setDraw(paint = paint)
    }

    private fun setDraw(
        x: Float = 0f,
        y: Float = 0f,
        paint: Paint,
    ) {
        currentDraw =
            when (brush.brushType) {
                BrushType.PEN -> {
                    val line = Line(brushType = BrushType.PEN, paint = paint)
                    line.move(x, y)
                    line
                }

                BrushType.RECTANGLE -> Rectangle(RectF(x, y, x, y), paint)
                BrushType.CIRCLE -> Circle(x, y, 0f, paint)
                BrushType.ERASER -> {
                    val line = Line(brushType = BrushType.ERASER, paint = paint)
                    line.move(x, y)
                    line
                }
            }
    }

    private fun setDraw(paint: Paint) {
        currentDraw =
            when (brush.brushType) {
                BrushType.PEN -> Line(brushType = BrushType.PEN, paint = paint)
                BrushType.RECTANGLE -> Rectangle(paint = paint)
                BrushType.CIRCLE -> Circle(paint = paint)
                BrushType.ERASER -> Line(brushType = BrushType.ERASER, paint = paint)
            }
    }
}
