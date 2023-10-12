package woowacourse.paint.model.drawingEngine.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import woowacourse.paint.model.drawingEngine.DrawingEngine
import woowacourse.paint.model.drawingEngine.ShapeDrawingEngine
import woowacourse.paint.model.pen.Pen

data class RectangleDrawingEngine(
    override val paint: Paint = Paint(),
    override val rectF: RectF = RectF(),
) : ShapeDrawingEngine() {

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }

    companion object {
        fun createInstance(pen: Pen, pointX: Float, pointY: Float): DrawingEngine {
            return RectangleDrawingEngine().apply {
                paint.color = pen.color
                changePosition(pointX, pointY, pointX, pointY)
            }
        }
    }
}
