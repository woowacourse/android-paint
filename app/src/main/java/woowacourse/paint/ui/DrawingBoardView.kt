package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Drawing

class DrawingBoardView constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private var path = Path()
    private val drawings = mutableListOf<Drawing>()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        initPaint()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path =
                    Path().apply {
                        moveTo(event.x, event.y)
                    }
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                drawings.add(Drawing(path, Paint(paint)))
            }

            else -> super.onTouchEvent(event)
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach { canvas.drawPath(it.path, it.paint) }
        canvas.drawPath(path, paint)
    }

    private fun initPaint() {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
    }

    fun setPaintColor(color: Int) {
        paint.color = color
    }

    fun setPaintStrokeSize(size: Float) {
        paint.strokeWidth = size
    }
}
