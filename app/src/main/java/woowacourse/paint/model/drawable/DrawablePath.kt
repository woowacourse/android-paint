package woowacourse.paint.model.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt
import woowacourse.paint.model.BrushSize

data class DrawablePath(
    private val path: Path = Path(),
    override val paint: Paint = Paint(),
) : DrawableElement {

    init {
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
    }

    override fun drawCurrent(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun startDrawing(x: Float, y: Float): DrawablePath {
        val newPath = Path().apply {
            moveTo(x, y)
            lineTo(x, y)
        }
        return DrawablePath(newPath, Paint(paint))
    }

    override fun keepDrawing(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun changePaintColor(@ColorInt color: Int): DrawablePath {
        return copy(
            paint = Paint(paint).apply { this.color = color },
        )
    }

    fun changeBrushSize(brushSize: BrushSize): DrawablePath {
        return copy(
            paint = Paint(paint).apply { strokeWidth = brushSize.width },
        )
    }
}
