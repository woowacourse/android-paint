package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import woowacourse.paint.BrushType
import woowacourse.paint.BrushType.Companion.DEFAULT_BRUSH_TYPE
import woowacourse.paint.BrushType.Companion.changeBrushType
import woowacourse.paint.Color.Companion.DEFAULT_DRAWING_COLOR
import kotlin.math.max
import kotlin.math.min

class Drawing(val path: Path = Path(), val paint: Paint = Paint()) {
    init {
        initializePaint()
    }

    fun setupDefaultDrawing() {
        paint.apply {
            color = DEFAULT_DRAWING_COLOR
            style = Paint.Style.STROKE
        }
        changeBrushType(DEFAULT_BRUSH_TYPE)
    }

    fun actionDown(
        pointX: Float,
        pointY: Float,
    ) {
        when (BrushType.brushType) {
            BrushType.PEN -> {
                Drawings.addNewDrawing(this)
                moveTo(pointX, pointY)
            }
            BrushType.RECTANGLE -> Drawings.addNewDrawing(this)
            BrushType.CIRCLE -> Drawings.addNewDrawing(this)
            BrushType.ERASER -> {
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
        when (BrushType.brushType) {
            BrushType.PEN -> lineTo(pointX, pointY)
            BrushType.RECTANGLE -> drawRect(startX, startY, pointX, pointY)
            BrushType.CIRCLE -> drawCircle(startX, startY, pointX, pointY)
            BrushType.ERASER -> {}
        }
    }

    fun actionUp(): Drawing = Drawing(Path(), paint)

    fun updateStrokeWidth(strokeWidth: Float): Paint {
        val paint = Paint(paint)
        paint.strokeWidth = strokeWidth
        return paint
    }

    fun updateColor(color: Int): Paint {
        val paint = Paint(paint)
        paint.color = color
        return paint
    }

    fun updatePaintStyle(style: Paint.Style): Paint {
        val paint = Paint(paint)
        paint.style = style
        return paint
    }

    private fun initializePaint() =
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }

    private fun moveTo(
        pointX: Float,
        pointY: Float,
    ) {
        path.moveTo(pointX, pointY)
    }

    private fun lineTo(
        pointX: Float,
        pointY: Float,
    ) {
        path.lineTo(pointX, pointY)
    }

    private fun drawRect(
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

    private fun drawCircle(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ) {
        path.reset()
        path.addOval(left, top, right, bottom, Path.Direction.CW)
    }

    private fun findRemoveItem(
        x: Float,
        y: Float,
    ): Int {
        val bounds = RectF()

        for (index in Drawings.drawings.indices.reversed()) {
            val drawing = Drawings.drawings[index]
            drawing.computeBounds(bounds)

            if (bounds.contains(x, y)) return index
        }
        return DrawingBoard.INVALID_INDEX
    }

    private fun computeBounds(bounds: RectF) {
        path.computeBounds(bounds, true)
    }
}
