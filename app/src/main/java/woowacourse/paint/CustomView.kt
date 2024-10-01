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

class CustomView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    private val paths = mutableListOf<PathData>()
    private var currentPath = Path()
    val paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val currentColor = paint.color
        val currentStrokeWidth = paint.strokeWidth
        for (pathData in paths) {
            paint.color = pathData.color
            paint.strokeWidth = pathData.strokeWidth
            canvas.drawPath(pathData.path, paint)
        }
        paint.color = currentColor
        paint.strokeWidth = currentStrokeWidth
        canvas.drawPath(currentPath, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path()
                currentPath.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                finishDrawing()
            }

            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    fun startDrawing() {
        currentPath = Path()
    }

    private fun finishDrawing() {
        paths.add(PathData(currentPath, paint.color, paint.strokeWidth))
    }

    private fun setupPaint() {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        paint.isAntiAlias = true
    }
}
