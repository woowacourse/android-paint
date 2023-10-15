package woowacourse.paint.model.drawable.path

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.model.BrushSize
import woowacourse.paint.model.drawable.DrawableElement

data class DrawableEraser(
    private val path: Path = Path(),
    override val paint: Paint = Paint(),
) : DrawablePath {

    init {
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
            style = Paint.Style.STROKE
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }
    }

    override fun drawCurrent(canvas: Canvas) {
        canvas.drawPath(path, Paint(paint))
    }

    override fun startDrawing(x: Float, y: Float): DrawableElement {
        val newPath = Path().apply {
            moveTo(x, y)
            lineTo(x, y)
        }
        return DrawableEraser(newPath, paint)
    }

    override fun keepDrawing(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun changePaintColor(color: Int): DrawableElement {
        return copy(
            paint = Paint(paint).apply { this.color = color },
        )
    }

    override fun changeBrushSize(brushSize: BrushSize): DrawablePath {
        return copy(
            paint = Paint(paint).apply { strokeWidth = brushSize.width },
        )
    }
}
