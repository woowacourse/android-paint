package woowacourse.paint.model.drawingEngine.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import woowacourse.paint.model.drawingEngine.ShapeDrawingEngine

data class RectangleEraserDrawingEngine(
    override val paint: Paint = Paint(),
    override val rectF: RectF = RectF(),
) : ShapeDrawingEngine() {

    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }
}
