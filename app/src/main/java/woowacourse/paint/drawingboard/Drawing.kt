package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.BrushType.Companion.DEFAULT_BRUSH_TYPE
import woowacourse.paint.BrushType.Companion.changeBrushType
import woowacourse.paint.Color.Companion.DEFAULT_DRAWING_COLOR

abstract class Drawing(val path: Path = Path(), val paint: Paint = Paint()) {
    init {
        initializePaint()
    }

    abstract fun updateColor(color: Int): Drawing

    abstract fun updatePaintStyle(): Drawing

    fun setupDefaultDrawing() {
        paint.apply {
            strokeWidth = DEFAULT_STROKE_WIDTH
            color = DEFAULT_DRAWING_COLOR
            style = Paint.Style.STROKE
        }
        changeBrushType(DEFAULT_BRUSH_TYPE)
    }

    fun updateStrokeWidth(strokeWidth: Float): Paint {
        val paint = Paint(paint)
        paint.strokeWidth = strokeWidth
        return paint
    }

    fun actionDown(
        pointX: Float,
        pointY: Float,
    ) {
        when (this) {
            is Pen -> {
                Drawings.addNewDrawing(this)
                moveTo(pointX, pointY)
            }
            is Rectangle -> Drawings.addNewDrawing(this)
            is Circle -> Drawings.addNewDrawing(this)
            is Eraser -> {
                val removeItemIndex = findRemoveItem(pointX, pointY)
                if (removeItemIndex != DrawingBoard.INVALID_INDEX) {
                    Drawings.removeDrawing(removeItemIndex)
                }
            }
        }
    }

    fun actionMove(
        startX: Float,
        startY: Float,
        pointX: Float,
        pointY: Float,
    ) {
        when (this) {
            is Pen -> lineTo(pointX, pointY)
            is Rectangle -> drawRect(startX, startY, pointX, pointY)
            is Circle -> drawCircle(startX, startY, pointX, pointY)
        }
    }

    fun actionUp(): Drawing {
        return when (this) {
            is Pen -> Pen(Path(), paint)
            is Rectangle -> Rectangle(Path(), paint)
            is Circle -> Circle(Path(), paint)
            is Eraser -> Eraser(Path(), paint)
            else -> Pen(Path(), paint)
        }
    }

    private fun initializePaint() =
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }

    companion object {
        private const val DEFAULT_STROKE_WIDTH = 10f
    }
}
