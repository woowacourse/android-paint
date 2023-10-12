package woowacourse.paint.model.brush.shape

import android.view.MotionEvent
import woowacourse.paint.model.brush.Brush

interface ShapeBrush : Brush {

    var startX: Float
    var startY: Float

    override fun startDrawing(event: MotionEvent) {
        startX = event.x
        startY = event.y
    }
}
