package woowacourse.paint.view.model.pen

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.graphics.AutoSortedRect

class RectPen : Pen {
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private var path: Path = Path()
    private var lastPointX: Float = 0F
    private var lastPointY: Float = 0F

    override fun startPaint(pointX: Float, pointY: Float) {
        lastPointX = pointX
        lastPointY = pointY
    }

    override fun movePaint(pointX: Float, pointY: Float) {
        path.reset()
        path.addRect(
            AutoSortedRect(lastPointX, lastPointY, pointX, pointY).toRectF(), Path.Direction.CCW
        )
    }

    override fun cacheCurrentPaint() {
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun setStrokeWidth(strokeWidth: Float) {
    }

    override fun setColor(color: Int) {
        paint.color = color
    }
}
