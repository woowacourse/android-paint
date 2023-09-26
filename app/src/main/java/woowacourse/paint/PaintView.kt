package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var currentPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        pathEffect = CornerPathEffect(10F)
    }
    private var currentPath: Path = Path()
    private val paths: PaintPath = PaintPath()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPath(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startTouch(event.x, event.y)
                true
            }

            MotionEvent.ACTION_MOVE -> {
                moveTouch(event.x, event.y)
                true
            }

            MotionEvent.ACTION_UP -> {
                endTouch()
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    fun setColor(color: Int) {
        currentPaint.color = color
    }

    fun setWidth(strokeWidth: Float) {
        currentPaint.strokeWidth = strokeWidth
    }

    private fun startTouch(pointX: Float, pointY: Float) {
        currentPath.moveTo(pointX, pointY)
        currentPath.lineTo(
            pointX, pointY
        )
        invalidate()
    }

    private fun moveTouch(pointX: Float, pointY: Float) {
        currentPath.lineTo(
            pointX, pointY
        )
        invalidate()
    }

    private fun endTouch() {
        paths.add(currentPath to currentPaint)
        currentPath = Path()
        currentPaint = Paint(currentPaint)
    }

    private fun drawPath(canvas: Canvas) {
        paths.data.forEach {
            canvas.drawPath(it.first, it.second)
        }
        canvas.drawPath(currentPath, currentPaint)
    }
}
