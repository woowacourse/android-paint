package woowacourse.paint.model.drawingEngine.path

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.model.drawingEngine.DrawingEngine
import woowacourse.paint.model.drawingEngine.PathDrawingEngine
import woowacourse.paint.model.pen.Pen

data class PathEraserDrawingEngine(
    override val paint: Paint = Paint(),
    override val path: Path = Path(),
) : PathDrawingEngine() {

    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    companion object {
        fun createInstance(pen: Pen, pointX: Float, pointY: Float): DrawingEngine {
            val paint = pen.createPaint()
            return PathEraserDrawingEngine(paint = paint).apply {
                paint.color = pen.color
                setStartPoint(pointX, pointY)
                setLastPoint(pointX, pointY)
            }
        }
    }
}
