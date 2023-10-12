package woowacourse.paint.board

import android.view.MotionEvent
import android.view.View

class ThreePointerDragListener(
    private val targetView: View,
    private val screenWidth: Int,
    private val screenHeight: Int,
) {
    private var isInProgress: Boolean = false

    private lateinit var onScreenMoveListener: () -> Unit

    private var lastFocusX: Float = 0f
    private var lastFocusY: Float = 0f

    fun setOnScreenMoveListener(listener: () -> Unit) {
        onScreenMoveListener = listener
    }

    fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> {
                isInProgress = event.pointerCount == 3
                if (isInProgress) onDragBegin(event)
            }
            MotionEvent.ACTION_MOVE -> {
                if (isInProgress) onDrag(event)
            }
            MotionEvent.ACTION_POINTER_UP -> {
                isInProgress = event.pointerCount != 3
            }
        }
        return true
    }

    private fun onDragBegin(event: MotionEvent) {
        lastFocusX = getPointersFocusX(event)
        lastFocusY = getPointersFocusY(event)
    }

    /**
     * 이 함수에서 뜻하는 FocusX는 포인터들의 중심점(평균)을 뜻한다.
     */
    private fun getPointersFocusX(event: MotionEvent): Float {
        var sumX: Float = 0f
        for (pointerNumber in 0 until event.pointerCount) {
            sumX += event.getX(pointerNumber)
        }
        return sumX / event.pointerCount
    }

    private fun getPointersFocusY(event: MotionEvent): Float {
        var sumY: Float = 0f
        for (pointerNumber in 0 until event.pointerCount) {
            sumY += event.getY(pointerNumber)
        }
        return sumY / event.pointerCount
    }

    private fun onDrag(event: MotionEvent) {
        val currentFocusX = getPointersFocusX(event)
        val currentFocusY = getPointersFocusY(event)

        val destinationX = targetView.x + (lastFocusX - currentFocusX) * DRAG_ADJUST_VALUE
        val destinationY = targetView.y + (lastFocusY - currentFocusY) * DRAG_ADJUST_VALUE

        val destinationXPreventOverScroll = adjustXToPreventOverScroll(destinationX)
        val destinationYPreventOverScroll = adjustYToPreventOverScroll(destinationY)

        moveTargetView(
            destinationXPreventOverScroll,
            destinationYPreventOverScroll,
        )

        if (::onScreenMoveListener.isInitialized) onScreenMoveListener()

        lastFocusX = currentFocusX
        lastFocusY = currentFocusY
    }

    private fun moveTargetView(xMoveAmount: Float, yMoveAmount: Float) {
        targetView.animate()
            .x(xMoveAmount)
            .y(yMoveAmount)
            .setDuration(0)
            .start()
    }

    /**
     * 뷰의 x,y가 기준이므로 화면 기준 음수로 나타난다. 이를 보정하기 위하여 뷰관련 좌표값들은 -를 통해 부호를 변경 해주었다.
     */
    private fun adjustXToPreventOverScroll(destinationX: Float): Float {
        if (-destinationX <= 0) return 0f
        if (-destinationX + screenWidth >= targetView.width) return -(targetView.width - screenWidth).toFloat()
        return destinationX
    }

    /**
     * 뷰의 x,y가 기준이므로 화면 기준 음수로 나타난다. 이를 보정하기 위하여 뷰관련 좌표값들은 -를 통해 부호를 변경 해주었다.
     */
    private fun adjustYToPreventOverScroll(destinationY: Float): Float {
        if (-destinationY <= 0) return 0f
        if (-destinationY + screenHeight >= targetView.height) return -(targetView.height - screenHeight).toFloat()
        return destinationY
    }

    companion object {
        private const val DRAG_ADJUST_VALUE = 0.75f
    }
}
