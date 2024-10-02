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

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        brushHistory.last().apply {
            setColor(DEFAULT_COLOR)
            setThickness(DEFAULT_STROKE_WIDTH)
        }
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
            MotionEvent.ACTION_DOWN -> currentBrush.moveTo(pointX, pointY)
            MotionEvent.ACTION_MOVE -> currentBrush.lineTo(pointX, pointY)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    @SuppressLint("ResourceAsColor")
    fun changeColor(
        @ColorRes color: Int,
    ) {
        val newBrush = Brush(paint = Paint(brushHistory.last().paint))
        newBrush.setColor(color)
        brushHistory.add(newBrush)
    }

    fun changeThickness(size: Float) {
        val newBrush = Brush(paint = Paint(brushHistory.last().paint))
        newBrush.setThickness(size)
        brushHistory.add(newBrush)
    }

    companion object {
        private const val DEFAULT_STROKE_WIDTH = 10.0f
        private const val DEFAULT_COLOR = Color.RED
    }
}
