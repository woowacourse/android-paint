package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

class CirclePainting(
    private val path: Path = Path(),
    private val _paint: Paint,
) : Painting {

    override val paint: Paint
        get() = Paint(_paint)
    private var prevX: Float = 0F
    private var prevY: Float = 0F

    init {
        _paint.apply {
            style = Paint.Style.FILL
            xfermode = null
        }
    }

    override fun movePath(x: Float, y: Float): Painting {
        return CirclePainting(path = Path(), _paint = _paint).apply {
            prevX = x
            prevY = y
        }
    }

    override fun initPath(x: Float, y: Float) {
        path.reset()
        path.addOval(
            prevX,
            prevY,
            x,
            y,
            Path.Direction.CW,
        )
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, _paint)
    }

    fun setColor(@ColorInt color: Int): Painting = CirclePainting(
        _paint = Paint(_paint).apply {
            this.color = color
        },
    )

    override fun getNewPainting(): Painting {
        return CirclePainting(_paint = _paint)
    }
}
