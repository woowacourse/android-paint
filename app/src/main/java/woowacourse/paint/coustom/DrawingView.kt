package woowacourse.paint.coustom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paths = mutableListOf<Pair<Path, Paint>>()

    private var currentPath: Path = Path()
    private var currentPaint: Paint = initialPaint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for ((path, paint) in paths) {
            canvas.drawPath(path, paint)
        }

        canvas.drawPath(currentPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath =
                    Path().apply {
                        moveTo(pointX, pointY)
                    }
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                paths.add(Pair(currentPath, Paint(currentPaint)))
                currentPath = Path()
            }
        }

        invalidate()
        return true
    }

    private fun initialPaint(): Paint {
        return Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 50f
        }
    }

    fun setPaintColor(
        @ColorInt color: Int,
    ) {
        currentPaint.color = color
    }

    fun setPaintWidth(width: Int) {
        currentPaint.strokeWidth = width.toFloat()
    }
}
