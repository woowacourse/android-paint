package woowacourse.paint.view.model.pen

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.view.model.pen.ink.Ink

abstract class FigurePen(
    override val ink: Ink = Ink(
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        },
        Path()
    ),
    open val onAddInk: (Ink) -> Unit
) : DrawablePen {
    protected var lastPointX: Float = 0F
    protected var lastPointY: Float = 0F

    override fun startPaint(pointX: Float, pointY: Float) {
        lastPointX = pointX
        lastPointY = pointY
    }

    override fun movePaint(pointX: Float, pointY: Float) {
        ink.path.reset()
        draw(pointX, pointY)
    }

    override fun cacheCurrentPaint() {
        if (!ink.path.isEmpty) onAddInk(ink)
        ink.path.reset()
    }

    override fun setStrokeWidth(strokeWidth: Float) {
    }

    override fun setColor(color: Int) {
        ink.paint.color = color
    }

    abstract fun draw(pointX: Float, pointY: Float)
}
