package woowacourse.paint.model.brush.shape

import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

class CircleBrush(override var paint: Paint) : ShapeBrush {

    override var path: Path = Path()
    override var startX: Float = DEFAULT_COORDINATE
    override var startY: Float = DEFAULT_COORDINATE

    override fun moveDrawing(event: MotionEvent) {
        path.reset()
        path.addCircle(startX, startY, event.x - startX, Path.Direction.CCW)
    }

    companion object {
        private const val DEFAULT_COORDINATE = 0.0f
    }
}
