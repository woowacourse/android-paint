package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint
import woowacourse.paint.model.drawingEngine.path.LineDrawingEngine
import woowacourse.paint.model.pen.Pen

fun createDefaultDrawingEngine(pen: Pen, pointX: Float, pointY: Float): DrawingEngine =
    LineDrawingEngine.createInstance(pen, pointX, pointY)

sealed interface DrawingEngine {

    val paint: Paint

    fun draw(canvas: Canvas)

    fun setStartPoint(pointX: Float, pointY: Float)

    fun setEndPoint(pointX: Float, pointY: Float)
}
