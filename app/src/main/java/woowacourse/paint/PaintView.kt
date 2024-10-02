package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes

class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val brushHistory = mutableListOf(Brush())
    private var startX: Float = 0f
    private var startY: Float = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        brushHistory.last().apply {
            setColor(DEFAULT_COLOR)
            setThickness(DEFAULT_STROKE_WIDTH)
        }
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (brush in brushHistory) {
            brush.draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        val currentBrush = brushHistory.last()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = pointX
                startY = pointY
                currentBrush.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentBrush.saveMovement(startX, startY, pointX, pointY, false)
            }

            MotionEvent.ACTION_UP -> {
                currentBrush.saveMovement(startX, startY, pointX, pointY, true)
                brushHistory.add(newBrush())
            }

            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    @SuppressLint("ResourceAsColor")
    fun changeColor(
        @ColorRes color: Int,
    ) {
        val newBrush = newBrush()
        newBrush.setColor(color)
        brushHistory.add(newBrush)
    }

    fun changeThickness(size: Float) {
        val newBrush = newBrush()
        newBrush.setThickness(size)
        brushHistory.add(newBrush)
    }

    fun changeBrush(brushName: String) {
        val newBrush = newBrush()
        newBrush.setBrush(brushName)
        brushHistory.add(newBrush)
    }

    private fun newBrush() =
        Brush(
            paint = Paint(brushHistory.last().paint),
            brushState = brushHistory.last().brushState,
        )

    companion object {
        private const val DEFAULT_STROKE_WIDTH = 10.0f
        private const val DEFAULT_COLOR = Color.RED
    }
}
