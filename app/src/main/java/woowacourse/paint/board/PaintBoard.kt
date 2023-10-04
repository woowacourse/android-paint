package woowacourse.paint.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.paint.R
import woowacourse.paint.board.draw.GraphicObject
import woowacourse.paint.board.draw.Line
import woowacourse.paint.palette.Palette

class PaintBoard(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val screenWidth = resources.displayMetrics.widthPixels
    private val screenHeight = resources.displayMetrics.heightPixels
    private val expandedWidth = screenWidth * BOARD_WIDTH_EXPANSION_RATE
    private val expandedHeight = screenHeight * BOARD_HEIGHT_EXPANSION_RATE

    private val graphicObjects: MutableList<GraphicObject> = mutableListOf()

    @ColorRes
    private var currentSelectedColor: Int = R.color.black
    private var currentStrokeWidth: Float = 10f

    private lateinit var palette: Palette

    private val twoPointerDragScaleDetector =
        ScaleGestureDetector(
            context,
            TwoPointerDragListener(this, screenWidth, screenHeight) {
                palette.x = -x
                palette.y = -y
            },
        )

    init {
        addStickyPalette()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        adjustBoardSize()
        moveScreenToBoardCenter()
    }

    private fun adjustBoardSize() {
        layoutParams = FrameLayout.LayoutParams(expandedWidth, expandedHeight)
    }

    private fun moveScreenToBoardCenter() {
        moveBoardToCenter()
        movePaletteToCenter()
    }

    private fun moveBoardToCenter() {
        x = (-expandedWidth / 2 + screenWidth / 2).toFloat()
        y = (-expandedHeight / 2 + screenHeight / 2).toFloat()
    }

    private fun movePaletteToCenter() {
        palette.x = -(-expandedWidth / 2 + screenWidth / 2).toFloat()
        palette.y = -(-expandedHeight / 2 + screenHeight / 2).toFloat()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        twoPointerDragScaleDetector.onTouchEvent(event)
        return twoPointerDragScaleDetector.isInProgress || lineEvent(event)
    }

    private fun lineEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val line: Line = Line(
                Paint().apply { color = context.getColor(currentSelectedColor) },
                currentStrokeWidth,
                ::invalidate,
            )
            graphicObjects.add(line)
        }
        graphicObjects.last().onTouchEventAction(event)
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        graphicObjects.forEach { it.onDrawAction(canvas) }
    }

    private fun addStickyPalette() {
        palette = Palette(
            context = context,
            attrs = null,
            onSelectedColorIdChangedListener = ::onSelectedColorIdChangedListener,
            onStrokeWidthChangedListener = ::onStrokeWidthChangedListener,
        )
        palette.layoutParams = FrameLayout.LayoutParams(screenWidth, WRAP_CONTENT)
        addView(palette)
    }

    private fun onSelectedColorIdChangedListener(colorId: Int) {
        currentSelectedColor = colorId
    }

    private fun onStrokeWidthChangedListener(strokeWidth: Float) {
        currentStrokeWidth = strokeWidth
    }

    companion object {
        private const val BOARD_HEIGHT_EXPANSION_RATE = 5
        private const val BOARD_WIDTH_EXPANSION_RATE = 15
    }
}
