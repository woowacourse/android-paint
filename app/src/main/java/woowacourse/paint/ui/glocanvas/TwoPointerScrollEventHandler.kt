package woowacourse.paint.ui.glocanvas

import android.view.MotionEvent

class TwoPointerScrollEventHandler(
    private val lowerBound: Float,
    private val upperBound: Float,
    private val onScroll: (Float) -> Unit,
) {
    private var startY: Float = 0f
    private var _isScrolling: Boolean = false
    val isScrolling: Boolean
        get() = _isScrolling

    fun onTouchEvent(y: Float, event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> {
                _isScrolling = true
                startY = event.y
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                scroll(y, event.y)
                return true
            }

            MotionEvent.ACTION_UP -> {
                _isScrolling = false
                return true
            }
        }
        return false
    }

    private fun scroll(y: Float, scrollY: Float) {
        val difference = startY - scrollY
        var destinationY = y - difference
        if (destinationY > lowerBound) destinationY = lowerBound
        if (destinationY < upperBound) destinationY = upperBound
        startY = scrollY
        onScroll(destinationY)
    }
}
