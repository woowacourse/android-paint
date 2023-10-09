package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

class RectanglePainting(
    private val path: Path = Path(),
    private val _paint: Paint,
) : Painting {

    override val paint: Paint
        get() = _paint

    init {
        _paint.apply {
            style = Paint.Style.FILL
            xfermode = null
        }
    }

    override fun movePath(x: Float, y: Float): Painting {
        return RectanglePainting(path = Path(), _paint = _paint)
    }

    override fun initPath(prevX: Float, prevY: Float, x: Float, y: Float) {
        path.reset()
        path.addRect(
            java.lang.Float.min(prevX, x),
            java.lang.Float.min(prevY, y),
            java.lang.Float.max(prevX, x),
            java.lang.Float.max(prevY, y),
            Path.Direction.CW,
        )
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, _paint)
    }

    override fun setStroke(value: Float): Painting = RectanglePainting(
        _paint = Paint(_paint).apply {
            this.strokeWidth = value
        },
    )

    override fun setColor(@ColorInt color: Int): Painting = RectanglePainting(
        _paint = Paint(_paint).apply {
            this.color = color
        },
    )

    override fun getNewPainting(): Painting {
        return RectanglePainting(_paint = _paint)
    }
}
