package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

data class DrawingElement(
    private val path: Path = Path(),
    private val paint: Paint = initSetupPaint(),
) {

    fun movePath(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    fun initPath(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    private fun withNewPath(): DrawingElement {
        return this.copy(path = Path())
    }

    fun withNewPaint(): DrawingElement {
        return this.copy(paint = Paint(paint))
    }

    fun setStroke(value: Float) = withNewPath().apply {
        paint.strokeWidth = value
    }

    fun setColor(@ColorInt color: Int) = withNewPath().apply {
        paint.color = color
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    companion object {
        private fun initSetupPaint(): Paint {
            return Paint().apply {
                strokeWidth = 50.0f
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                color = Color.RED
            }
        }
    }
}
