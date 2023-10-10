package woowacourse.paint.canvas.drawing

import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

abstract class Drawing(val path: Path, val paint: Paint) {
    abstract fun onDraw()
    abstract fun onTouchEvent(event: MotionEvent): Boolean
}
