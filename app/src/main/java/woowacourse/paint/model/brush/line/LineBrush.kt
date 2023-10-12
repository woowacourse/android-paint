package woowacourse.paint.model.brush.line

import android.view.MotionEvent
import woowacourse.paint.model.brush.Brush

interface LineBrush : Brush {

    override fun startDrawing(event: MotionEvent) {
        path.moveTo(event.x, event.y)
        path.lineTo(event.x, event.y)
    }

    override fun moveDrawing(event: MotionEvent) {
        path.lineTo(event.x, event.y)
    }
}
