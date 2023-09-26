package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Canvas(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {
    private val pathPaint: MutableList<Pair<Path, Paint>> = mutableListOf()
    private var currentPaint: Paint = Paint()
    private var currentPath: Path = Path()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        initPaint()
        initPath()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        pathPaint.forEach {
            canvas.drawPath(it.first, it.second)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pathPaint.add(currentPath to currentPaint)
                currentPath.moveTo(event.x, event.y)
                currentPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                initPaint()
                initPath()
            }

            else -> {
                return super.onTouchEvent(event)
            }
        }
        invalidate()
        return true
    }

    private fun initPaint() {
        currentPaint = Paint(currentPaint).apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    private fun initPath() {
        currentPath = Path()
    }

    fun setStrokeSize(size: Float) {
        currentPaint.strokeWidth = size
    }
}
