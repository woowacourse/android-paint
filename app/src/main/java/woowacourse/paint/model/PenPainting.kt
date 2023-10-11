package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

class PenPainting(
    private val path: Path = Path(),
    private val _paint: Paint = Paint().applyPaintSetting(),
) : Painting {

    override val paint: Paint
        get() = Paint(_paint)

    init {
        _paint.apply {
            xfermode = null
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun movePath(x: Float, y: Float): Painting {
        return PenPainting(path = Path(), _paint = _paint).apply {
            this.path.moveTo(x, y)
        }
    }

    override fun initPath(prevX: Float, prevY: Float, x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, _paint)
    }

    fun setStroke(value: Float): Painting = PenPainting(
        path = Path(),
        _paint = Paint(_paint).apply {
            this.strokeWidth = value
        },
    )

    fun setColor(@ColorInt color: Int): Painting = PenPainting(
        path = Path(),
        _paint = Paint(_paint).apply {
            this.color = color
        },
    )

    override fun getNewPainting(): Painting {
        return PenPainting(_paint = _paint)
    }

    companion object {
        fun Paint.applyPaintSetting(
            @ColorInt paintColor: Int = Color.RED,
            paintWidth: Float = 50.0f,
            paintStyle: Paint.Style = Paint.Style.STROKE,
            paintCap: Paint.Cap = Paint.Cap.ROUND,
        ): Paint = this.apply {
            isAntiAlias = true
            xfermode = null
            strokeWidth = paintWidth
            strokeJoin = Paint.Join.ROUND
            style = paintStyle
            strokeCap = paintCap
            color = paintColor
        }
    }
}
