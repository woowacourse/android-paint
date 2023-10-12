package woowacourse.paint.model.brush

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.model.drawing.Drawing
import woowacourse.paint.model.drawing.DrawingHistory
import woowacourse.paint.model.drawing.PathPoint

class Pen(private var paint: Paint) : Brush {
    private var path: Path = Path()

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun startDrawing(point: PathPoint) {
        path.moveTo(point.x, point.y)
    }

    override fun moveDrawing(point: PathPoint) {
        path.lineTo(point.x, point.y)
    }

    override fun endDrawing(drawingHistory: DrawingHistory) {
        drawingHistory.addDrawing(Drawing(path, paint))
        this.path = Path()
    }

    override fun changePaint(paint: Paint) {
        this.paint = paint
    }
}
