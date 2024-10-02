package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes

class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val pathHistory = mutableListOf<Pair<Path, Paint>>()
    private var path = Path()
    private val paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for ((path, paint) in pathHistory) {
            canvas.drawPath(path, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path = Path()
                path.moveTo(pointX, pointY)
                pathHistory.add(Pair(path, Paint(paint)))
            }

            MotionEvent.ACTION_MOVE -> path.lineTo(pointX, pointY)

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    @SuppressLint("ResourceAsColor")
    fun setPaint(
        @ColorRes color: Int,
    ) {
        paint.color = color
    }

    fun setThickness(size: Float) {
        paint.strokeWidth = size
    }

    private fun setupPaint() {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = DEFAULT_STROKE_WIDTH
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
    }

    companion object {
        private const val DEFAULT_STROKE_WIDTH = 10.0f
    }
}
