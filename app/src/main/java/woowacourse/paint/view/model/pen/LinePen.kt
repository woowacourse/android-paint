package woowacourse.paint.view.model.pen

import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path

class LinePen(
    override val ink: Ink = Ink(
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            pathEffect = CornerPathEffect(10F)
        },
        Path()
    ),
    val onAddInk: (Ink) -> Unit = { _ -> }
) : Pen {

    override fun startPaint(pointX: Float, pointY: Float) {
        ink.path.moveTo(pointX, pointY)
        ink.path.lineTo(pointX, pointY)
    }

    override fun movePaint(pointX: Float, pointY: Float) {
        ink.path.lineTo(pointX, pointY)
    }

    override fun cacheCurrentPaint() {
        onAddInk(ink)
    }

    override fun setStrokeWidth(strokeWidth: Float) {
        ink.paint.strokeWidth = strokeWidth
    }

    override fun setColor(color: Int) {
        ink.paint.color = color
    }
}
