package woowacourse.paint.model.brush

import android.graphics.Canvas
import android.graphics.Paint
import woowacourse.paint.model.drawing.DrawingHistory
import woowacourse.paint.model.drawing.PathPoint

interface Brush {
    fun draw(canvas: Canvas)
    fun startDrawing(point: PathPoint)
    fun moveDrawing(point: PathPoint)
    fun endDrawing(drawingHistory: DrawingHistory)
    fun changePaint(paint: Paint)
}
