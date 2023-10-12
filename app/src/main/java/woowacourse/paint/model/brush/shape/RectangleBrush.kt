package woowacourse.paint.model.brush.shape

import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

class RectangleBrush(override var paint: Paint) : ShapeBrush {

    override var path: Path = Path()
    override var startX: Float = DEFAULT_COORDINATE
    override var startY: Float = DEFAULT_COORDINATE

    override fun moveDrawing(event: MotionEvent) {
        path.reset()
        path.addRect(startX, startY, event.x, event.y, Path.Direction.CCW)
    }

    companion object {
        private const val DEFAULT_COORDINATE = 0.0f
    }
}
