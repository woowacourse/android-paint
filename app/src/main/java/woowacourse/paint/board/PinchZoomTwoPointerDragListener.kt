package woowacourse.paint.board

import android.view.ScaleGestureDetector
import android.view.View

/**
 * 그림판에 적용하기 위한 ScaleGestureListener로 두가지 사양을 제공한다.
 * 1.핀치줌 : 어느 지점이든 핀치줌을 사용할 수 있다.
 * 2.두 손가락 드래그 : 그림판이기 때문에 두 손가락을 이용해서 드래그 및 이동을 할수 있도록 설정 하였다.
 */
class PinchZoomTwoPointerDragListener(private val targetView: View) :
    ScaleGestureDetector.SimpleOnScaleGestureListener() {

    private var lastFocusX: Float = 0f
    private var lastFocusY: Float = 0f

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        lastFocusX = detector.focusX
        lastFocusY = detector.focusY
        return true
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        val currentFocusX = detector.focusX
        val currentFocusY = detector.focusY

        targetView.animate()
            .x(targetView.x + (lastFocusX - currentFocusX) * DRAG_ADJUST_VALUE)
            .y(targetView.y + (lastFocusY - currentFocusY) * DRAG_ADJUST_VALUE)
            .setDuration(0)
            .start()

        lastFocusX = currentFocusX
        lastFocusY = currentFocusY

        return true
    }

    companion object {
        private const val DRAG_ADJUST_VALUE = 0.75f
    }
}
