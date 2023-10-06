package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

data class DrawablePath(
    val path: Path,
    val paint: Paint,
) {
    fun initPaint() {
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
    }

    fun drawCurrentPath(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    fun startDrawing(x: Float, y: Float): DrawablePath {
        return DrawablePath(
            path = Path().apply {
                moveTo(x, y)
                lineTo(x, y)
            },
            paint = Paint(paint),
        )
    }

    fun keepDrawing(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    fun changeBrushSize(brushSize: BrushSize): DrawablePath {
        return copy(
            paint = Paint(paint).apply { strokeWidth = brushSize.width },
        )
    }

    fun changePaintColor(@ColorInt color: Int): DrawablePath {
        return copy(
            paint = Paint(paint).apply { this.color = color },
        )
    }
}
