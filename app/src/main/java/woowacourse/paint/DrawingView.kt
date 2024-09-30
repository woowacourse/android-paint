package woowacourse.paint // 실제 패키지 이름으로 변경하세요.

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    private val strokes = mutableListOf<Stroke>()
    private var currentPath: Path? = null
    private var currentPaint: Paint? = null

    private var currentColor = Color.BLACK
    private var currentStrokeWidth = 10f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath =
                    Path().apply {
                        moveTo(x, y)
                    }
                currentPaint =
                    Paint().apply {
                        color = currentColor
                        style = Paint.Style.STROKE
                        strokeWidth = currentStrokeWidth
                        isAntiAlias = true
                    }
                strokes.add(Stroke(currentPath!!, currentPaint!!))
                invalidate()
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath?.lineTo(x, y)
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                currentPath = null
                currentPaint = null
                return true
            }

            else -> return false
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (stroke in strokes) {
            canvas.drawPath(stroke.path, stroke.paint)
        }
    }

    fun setBrushColor(color: Int) {
        currentColor = color
    }

    fun setBrushSize(size: Float) {
        currentStrokeWidth = size
    }
}
