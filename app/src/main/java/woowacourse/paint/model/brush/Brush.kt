package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import woowacourse.paint.model.Drawing
import woowacourse.paint.model.DrawingHistory

interface Brush {

    var path: Path
    var paint: Paint

    fun setPaintingOption(paint: Paint) {
        this.paint.apply {
            xfermode = null
            this.color = paint.color
            this.strokeWidth = paint.strokeWidth
        }
    }

    fun startDrawing(event: MotionEvent)
    fun moveDrawing(event: MotionEvent)
    fun endDrawing(drawingHistory: DrawingHistory) {
        drawingHistory.add(Drawing(path, paint))
        paint = Paint(paint)
        path = Path()
    }
}
