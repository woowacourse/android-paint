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

abstract class DiagramView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    protected val path = Path()
    protected val paint = Paint()
    private var startX: Float = 0f
    private var startY: Float = 0f

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
                startX = pointX
                startY = pointY
                path.moveTo(startX, startY)
            }

            MotionEvent.ACTION_MOVE -> {
                clear()
                drawDiagram(
                    startX,
                    startY,
                    pointX,
                    pointY,
                )
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return false
    }

    private fun setupPaint() {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 30f
        paint.isAntiAlias = true
        paint.isDither = true
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
    }

    abstract fun drawDiagram(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    )

    fun setColor(color: Int) {
        paint.setColor(color)
    }

    fun clear() {
        path.reset()
    }
}
