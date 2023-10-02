package woowacourse.paint.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.paint.board.draw.GraphicObject
import woowacourse.paint.board.draw.Line

class PaintBoard(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val screenWidth = resources.displayMetrics.widthPixels
    private val screenHeight = resources.displayMetrics.heightPixels
    private val expandedWidth = screenWidth * BOARD_WIDTH_EXPANSION_RATE
    private val expandedHeight = screenHeight * BOARD_HEIGHT_EXPANSION_RATE

    private val graphicObjects: MutableList<GraphicObject> = mutableListOf()

    private val twoPointerDragScaleDetector =
        ScaleGestureDetector(
            context,
            TwoPointerDragListener(this, screenWidth, screenHeight),
        )

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
        twoPointerDragScaleDetector.onTouchEvent(event)
        return twoPointerDragScaleDetector.isInProgress || lineEvent(event)
    }

    private fun lineEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val line: Line = Line(Paint(), 10f, ::invalidate)
            graphicObjects.add(line)
        }
        graphicObjects.last().onTouchEventAction(event)
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        graphicObjects.forEach { it.onDrawAction(canvas) }
    }

    companion object {
        private const val BOARD_HEIGHT_EXPANSION_RATE = 5
        private const val BOARD_WIDTH_EXPANSION_RATE = 15
    }
}
