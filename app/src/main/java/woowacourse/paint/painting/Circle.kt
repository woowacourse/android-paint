package woowacourse.paint.painting

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF

class Circle(
    path: Path = Path(),
    paintColor: Int,
    paintWidth: Float,
) : Painting(
    path = path,
    paint = Paint().apply {
        strokeWidth = paintWidth
        color = paintColor
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        xfermode = null
    },
) {
    private var startPointX: Float = INIT_POINT
    private var startPointY: Float = INIT_POINT
    private val rect = RectF()

    override fun onActionDown(x: Float, y: Float) {
        startPointX = x
        startPointY = y
    }

    override fun onActionMove(x: Float, y: Float) {
        rect.set(startPointX, startPointY, x, y)
    }

    override fun getInitializedPathPainting(): Painting {
        return Circle(Path(), paint.color, paint.strokeWidth)
    }

    override fun drawOnCanvas(canvas: Canvas) {
        canvas.drawArc(rect, START_ANGLE, SWEEP_ANGLE, true, paint)
    }

    companion object {
        private const val INIT_POINT = 0F
        private const val START_ANGLE = 0F
        private const val SWEEP_ANGLE = 360f
    }
}
