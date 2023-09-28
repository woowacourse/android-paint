package woowacourse.paint.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import woowacourse.paint.model.DrawablePath
import woowacourse.paint.model.DrawablePathHistory

class PaintBoard constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val pathHistory = DrawablePathHistory()
    private var currentPath = Path()
    private val currentPaint = Paint()

    init {
        setDefaultPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        pathHistory.paths.forEach {
            canvas.drawPath(it.path, it.paint)
        }
        canvas.drawPath(currentPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startDrawing(event)
            MotionEvent.ACTION_MOVE -> moveDrawing(event)
            MotionEvent.ACTION_UP -> endDrawing()
            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun setDefaultPaint() {
        currentPaint.apply {
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
    }

    private fun startDrawing(event: MotionEvent) {
        currentPath = Path()
        currentPath.moveTo(event.x, event.y)
        currentPath.lineTo(event.x, event.y)
    }

    private fun moveDrawing(event: MotionEvent) {
        currentPath.lineTo(event.x, event.y)
    }

    private fun endDrawing() {
        pathHistory.add(DrawablePath(currentPath, Paint(currentPaint)))
    }

    fun setBrushSize(size: Float) {
        currentPaint.strokeWidth = size
    }

    fun setBrushColor(@ColorInt color: Int) {
        currentPaint.color = color
    }
}
