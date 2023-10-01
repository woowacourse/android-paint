package woowacourse.paint.board

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

class PaintBoard(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val screenWidth = resources.displayMetrics.widthPixels
    private val screenHeight = resources.displayMetrics.heightPixels
    private val expandedWidth = screenWidth * BOARD_WIDTH_EXPANSION_RATE
    private val expandedHeight = screenHeight * BOARD_HEIGHT_EXPANSION_RATE

    private val pinchZoomTwoPointerDragScaleDetector =
        ScaleGestureDetector(context, PinchZoomTwoPointerDragListener(this))

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        adjustBoardSize()
        moveScreenToBoardCenter()
    }

    private fun adjustBoardSize() {
        layoutParams = FrameLayout.LayoutParams(expandedWidth, expandedHeight)
    }

    private fun moveScreenToBoardCenter() {
        x = (-expandedWidth / 2 + screenWidth / 2).toFloat()
        y = (-expandedHeight / 2 + screenHeight / 2).toFloat()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        pinchZoomTwoPointerDragScaleDetector.onTouchEvent(event)
        return true
    }

    companion object {
        private const val BOARD_HEIGHT_EXPANSION_RATE = 5
        private const val BOARD_WIDTH_EXPANSION_RATE = 15
    }
}
