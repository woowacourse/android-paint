package woowacourse.paint.model

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import kotlin.math.pow
import kotlin.math.sqrt

data class Brush(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) {
    init {
        paint.isAntiAlias = true
    }

    fun configureBrush(brushStyle: BrushStyle) {
        paint.color = brushStyle.color
        paint.strokeWidth = brushStyle.strokeWidth

        when (brushStyle.brushType) {
            BrushType.PEN -> configurePenBrush()
            BrushType.RECTANGLE -> configureRectangleBrush()
            BrushType.CIRCLE -> configureCircleBrush()
            BrushType.ERASER -> configureEraserBrush()
        }
    }

    private fun configurePenBrush() {
        paint.style = Paint.Style.STROKE
    }

    private fun configureRectangleBrush() {
        paint.style = Paint.Style.FILL
    }

    private fun configureCircleBrush() {
        paint.style = Paint.Style.FILL
    }

    private fun configureEraserBrush() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 50f
            color = Color.WHITE
        }
    }

    fun move(
        brushType: BrushType,
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        isEnd: Boolean,
    ) {
        when (brushType) {
            BrushType.PEN -> movePen(endX, endY)
            BrushType.RECTANGLE -> moveRectangle(startX, startY, endX, endY, isEnd)
            BrushType.CIRCLE -> moveCircle(startX, startY, endX, endY, isEnd)
            BrushType.ERASER -> moveEraser(endX, endY)
        }
    }

    private fun movePen(
        endX: Float,
        endY: Float,
    ) {
        path.lineTo(endX, endY)
    }

    private fun moveRectangle(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        isEnd: Boolean,
    ) {
        if (!isEnd) path.reset()
        path.addRect(startX, startY, endX, endY, Path.Direction.CCW)
    }

    private fun moveCircle(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        isEnd: Boolean,
    ) {
        if (!isEnd) path.reset()
        val radius = calculateRadius(startX, startY, endX, endY)
        path.addCircle(startX, startY, radius, Path.Direction.CCW)
    }

    private fun moveEraser(
        endX: Float,
        endY: Float,
    ) {
        path.lineTo(endX, endY)
    }

    private fun calculateRadius(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ): Float = sqrt((endX - startX).toDouble().pow(2.0) + (endY - startY).toDouble().pow(2.0)).toFloat()
}
