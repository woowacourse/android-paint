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

class PaintingView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val path = Path()
    private val paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true

        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                path.lineTo(pointX, pointY)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun setupPaint() {
        paint.color = Color.RED
        paint.isAntiAlias = true
        paint.strokeWidth = 20f
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
    }
}
