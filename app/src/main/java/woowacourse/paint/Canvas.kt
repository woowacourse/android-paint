package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.PathPaint

class Canvas(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {
    private val pathPaints: MutableList<PathPaint> = mutableListOf()
    private var currentPathPaint: PathPaint = PathPaint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        initPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        pathPaints.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pathPaints.add(currentPathPaint)
                currentPathPaint.moveToPath(event.x, event.y)
                currentPathPaint.lineToPath(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPathPaint.lineToPath(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                initPaint()
            }

            else -> {
                return super.onTouchEvent(event)
            }
        }
        invalidate()
        return true
    }

    private fun initPaint() {
        currentPathPaint = currentPathPaint.resetPaint().apply {
            with(paint) {
                isAntiAlias = true
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
            }
        }
    }

    fun setStrokeSize(size: Float) {
        currentPathPaint.setPaintStrokeSize(size)
    }

    fun setPaintColor(color: Int) {
        currentPathPaint.setPaintColor(color)
    }
}
