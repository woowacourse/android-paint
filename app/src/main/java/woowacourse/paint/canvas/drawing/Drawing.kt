package woowacourse.paint.canvas.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

abstract class Drawing(val paint: Paint) {
    val path = Path()
    fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    abstract fun onTouchEvent(event: MotionEvent): Boolean
}
