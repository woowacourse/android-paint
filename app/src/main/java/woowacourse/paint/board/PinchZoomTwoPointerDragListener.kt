package woowacourse.paint.board

import android.view.ScaleGestureDetector
import android.view.View

/**
 * 그림판에 적용하기 위한 ScaleGestureListener로 두가지 사양을 제공한다.
 * 1.핀치줌 : 어느 지점이든 핀치줌을 사용할 수 있다.
 * 2.두 손가락 드래그 : 그림판이기 때문에 두 손가락을 이용해서 드래그 및 이동을 할수 있도록 설정 하였다.
 */
class PinchZoomTwoPointerDragListener(
    private val targetView: View,
    private val screenWidth: Int,
    private val screenHeight: Int,
) :
    ScaleGestureDetector.SimpleOnScaleGestureListener() {

    private var lastFocusX: Float = 0f
    private var lastFocusY: Float = 0f

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        twoPointerDragOnScaleBegin(detector)
        return true
    }

    private fun twoPointerDragOnScaleBegin(detector: ScaleGestureDetector) {
        lastFocusX = detector.focusX
        lastFocusY = detector.focusY
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        twoPointerDragOnScale(detector)
        return true
    }

    private fun twoPointerDragOnScale(detector: ScaleGestureDetector) {
        val currentFocusX = detector.focusX
        val currentFocusY = detector.focusY

        val destinationX = targetView.x + (lastFocusX - currentFocusX) * DRAG_ADJUST_VALUE
        val destinationY = targetView.y + (lastFocusY - currentFocusY) * DRAG_ADJUST_VALUE

        val destinationXPreventOverScroll = adjustXToPreventOverScroll(destinationX)
        val destinationYPreventOverScroll = adjustYToPreventOverScroll(destinationY)

        moveTargetView(
            destinationXPreventOverScroll,
            destinationYPreventOverScroll,
        )

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
