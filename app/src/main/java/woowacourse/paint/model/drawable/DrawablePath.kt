package woowacourse.paint.model.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt
import woowacourse.paint.model.BrushSize

data class DrawablePath(
    val path: Path,
    override val paint: Paint,
) : DrawableElement {

    override fun drawCurrent(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun startDrawing(x: Float, y: Float): DrawablePath {
        return DrawablePath(
            path = Path().apply {
                moveTo(x, y)
                lineTo(x, y)
            },
            paint = Paint(paint),
        )
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

    companion object {
        fun from(paint: Paint = Paint()) = DrawablePath(
            path = Path(),
            paint = Paint(paint).apply {
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
                isAntiAlias = true
                style = Paint.Style.STROKE
            },
        )
    }
}
