package woowacourse.paint.ui.glocanvas.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

interface Drawing {
    val paint: Paint
    val invalidate: () -> Unit
    fun onDraw(canvas: Canvas)
    fun onTouchEvent(event: MotionEvent): Boolean
}
