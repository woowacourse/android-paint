package woowacourse.paint.model.brush

import android.graphics.Canvas
import android.graphics.Paint
import woowacourse.paint.model.drawing.DrawingHistory

interface PathBrush : Brush {

    fun draw(canvas: Canvas)
    fun endDrawing(drawingHistory: DrawingHistory)
    fun changePaint(paint: Paint)
}
