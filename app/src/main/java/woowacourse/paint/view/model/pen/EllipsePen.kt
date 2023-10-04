package woowacourse.paint.view.model.pen

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.graphics.AutoSortedRect
import woowacourse.paint.view.model.pen.ink.Ink

class EllipsePen(
    override val ink: Ink = Ink(
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        },
        Path()
    ),
    val onAddInk: (Ink) -> Unit = { _ -> }
) : Pen {
    private var lastPointX: Float = 0F
    private var lastPointY: Float = 0F

    override fun startPaint(pointX: Float, pointY: Float) {
        lastPointX = pointX
        lastPointY = pointY
    }

    override fun movePaint(pointX: Float, pointY: Float) {
        ink.path.reset()
        ink.path.addOval(
            AutoSortedRect(lastPointX, lastPointY, pointX, pointY).toRectF(), Path.Direction.CCW
        )
    }

    override fun cacheCurrentPaint() {
        onAddInk(ink)
    }

    override fun setStrokeWidth(strokeWidth: Float) {
    }

    override fun setColor(color: Int) {
        ink.paint.color = color
    }
}
