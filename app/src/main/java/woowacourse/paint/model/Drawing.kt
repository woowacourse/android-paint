package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import kotlin.math.sqrt

data class Drawing(
    val paint: Paint = Paint(),
    private val path: Path = Path(),
    var brushMode: BrushMode = BrushMode.PEN,
) {
    fun moveTo(
        x: Float,
        y: Float,
    ) {
        path.moveTo(x, y)
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    fun saveMovement(
        startX: Float,
        startY: Float,
        pointX: Float,
        pointY: Float,
        b: Boolean,
    ) {
        when (brushMode) {
            BrushMode.PEN -> path.lineTo(pointX, pointY)
            BrushMode.RECT -> {
                if (!b) path.reset()
                paint.style = Paint.Style.FILL
                path.addRect(startX, startY, pointX, pointY, Path.Direction.CCW)
            }
            BrushMode.CIRCLE -> {
                if (!b) path.reset()
                paint.style = Paint.Style.FILL
                path.addCircle(startX, startY, calculateRadius(startX, startY, pointX, pointY), Path.Direction.CCW)
            }
            BrushMode.ERASER -> {
                paint.style = Paint.Style.STROKE
                paint.color = BACKGROUND_COLOR
                path.lineTo(pointX, pointY)
            }
        }
    }

    private fun calculateRadius(
        startX: Float,
        startY: Float,
        pointX: Float,
        pointY: Float,
    ): Float {
        val dx = startX - pointX
        val dy = startY - pointY
        return sqrt((dx * dx + dy * dy).toDouble()).toFloat()
    }

    fun changeColor(color: Int) {
        paint.color = color
    }

    fun changeStrokeWidth(size: Float) {
        paint.strokeWidth = size
    }

    fun changePaintStyle(style: Paint.Style) {
        paint.style = style
    }

    companion object {
        private const val DEFAULT_STROKE_WIDTH = 5f
        private const val DEFAULT_COLOR = Color.RED
        private const val BACKGROUND_COLOR = Color.WHITE
        private val DEFAULT_STYLE = Paint.Style.STROKE
    }
}
