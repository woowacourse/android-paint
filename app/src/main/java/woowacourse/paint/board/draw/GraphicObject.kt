package woowacourse.paint.board.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

interface GraphicObject {
    val paint: Paint
    fun onTouchEventAction(event: MotionEvent): Boolean
    fun onDrawAction(canvas: Canvas)
}
