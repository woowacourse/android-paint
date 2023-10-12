package woowacourse.paint.model.drawingEngine.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import woowacourse.paint.model.drawingEngine.ShapeDrawingEngine

data class OvalDrawingEngine(
    override val paint: Paint = Paint(),
    override val rectF: RectF = RectF()
) : ShapeDrawingEngine() {

    override fun draw(canvas: Canvas) {
        canvas.drawOval(rectF, paint)
    }
}
