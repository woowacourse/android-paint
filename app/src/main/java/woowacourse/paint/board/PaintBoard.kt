package woowacourse.paint.board

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

class PaintBoard(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val screenWidth = resources.displayMetrics.widthPixels
    private val screenHeight = resources.displayMetrics.heightPixels
    private val expandedWidth = screenWidth * BOARD_EXPANSION_RATE
    private val expandedHeight = screenHeight * BOARD_EXPANSION_RATE

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        adjustBoardSize()
        moveScreenToBoardCenter()
    }

    private fun adjustBoardSize() {
        layoutParams = FrameLayout.LayoutParams(expandedWidth, expandedHeight)
    }

    private fun moveScreenToBoardCenter() {
        translationX = (-expandedWidth / 2 + screenWidth / 2).toFloat()
        translationY = (-expandedHeight / 2 + screenHeight / 2).toFloat()
    }

    companion object {
        private const val BOARD_EXPANSION_RATE = 5
    }
}
