package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.annotation.ColorInt
import java.lang.Float.max
import java.lang.Float.min

data class DrawingElement(
    private val brushTool: BrushTool = BrushTool.PEN,
    private val path: Path = Path(),
    private val paint: Paint = initSetupPaint(),
) {

    fun movePath(x: Float, y: Float): DrawingElement {
        return when (brushTool) {
            BrushTool.PEN, BrushTool.ERASER -> withNewPath().apply {
                this.path.moveTo(x, y)
            }

            BrushTool.CIRCLE, BrushTool.RECTANGLE -> withNewPath()
        }
    }

    fun initPath(prevX: Float, prevY: Float, x: Float, y: Float) {
        when (brushTool) {
            BrushTool.PEN -> drawLine(x, y)
            BrushTool.RECTANGLE -> drawShape {
                addRect(
                    min(prevX, x),
                    min(prevY, y),
                    max(prevX, x),
                    max(prevY, y),
                    Path.Direction.CW,
                )
            }

            BrushTool.CIRCLE -> drawShape {
                addOval(
                    prevX,
                    prevY,
                    x,
                    y,
                    Path.Direction.CW,
                )
            }

            BrushTool.ERASER -> erase(x, y)
        }
    }

    private fun drawLine(x: Float, y: Float) {
        paint.xfermode = null
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        path.lineTo(x, y)
    }

    private fun drawShape(drawCommand: Path.() -> Unit) {
        path.reset()
        paint.xfermode = null
        paint.style = Paint.Style.FILL
        path.drawCommand()
    }

    private fun erase(x: Float, y: Float) {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.SQUARE
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

    fun setBrush(brushTool: BrushTool) = this.copy(brushTool = brushTool)

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    companion object {
        private fun initSetupPaint(): Paint {
            return Paint().apply {
                isAntiAlias = true
                strokeWidth = 50.0f
                strokeJoin = Paint.Join.ROUND
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                color = Color.RED
            }
        }
    }
}
