package woowacourse.paint.model.brush.shape

import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import kotlin.math.max
import kotlin.math.min

class RectangleBrush(override var paint: Paint) : ShapeBrush {

    override var path: Path = Path()
    override var startX: Float = DEFAULT_COORDINATE
    override var startY: Float = DEFAULT_COORDINATE

    override fun moveDrawing(event: MotionEvent) {
        path.reset()
        path.addRect(
            min(startX, event.x),
            min(startY, event.y),
            max(startX, event.x),
            max(startY, event.y),
            Path.Direction.CCW,
        )
    }

    companion object {
        private const val DEFAULT_COORDINATE = 0.0f
    }
}
