package woowacourse.paint.model.drawingEngine.path

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.model.drawingEngine.DrawingEngine
import woowacourse.paint.model.drawingEngine.PathDrawingEngine
import woowacourse.paint.model.pen.Pen

data class LineDrawingEngine(
    override val paint: Paint = Paint(),
    override val path: Path = Path(),
) : PathDrawingEngine() {

    companion object {
        fun createInstance(pen: Pen, pointX: Float, pointY: Float): DrawingEngine {
            val paint = pen.createPaint()
            return LineDrawingEngine(paint = paint).apply {
                paint.color = pen.color
                setStartPoint(pointX, pointY)
                setLastPoint(pointX, pointY)
            }
        }
    }
}
