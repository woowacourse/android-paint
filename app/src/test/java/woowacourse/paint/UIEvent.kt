package woowacourse.paint

import android.os.SystemClock
import android.view.MotionEvent
import android.view.View

object UIEvent {
    fun View.drag() {
        val downTime = SystemClock.uptimeMillis()
        dispatchTouchEvent(
            MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, 10F, 10F, 0)
        )
        dispatchTouchEvent(
            MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_MOVE, 15F, 10F, 0)
        )
        dispatchTouchEvent(
            MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_UP, 10F, 10F, 0)
        )
    }
}
