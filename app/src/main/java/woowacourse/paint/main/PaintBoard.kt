package woowacourse.paint.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.model.BrushSize
import woowacourse.paint.model.DrawablePath
import woowacourse.paint.model.DrawablePathHistory
import woowacourse.paint.model.PaintColor

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

    fun setBrushSize(size: BrushSize?) {
        if (size == null) return
        currentPaint.strokeWidth = size.width
    }

    fun setBrushColor(color: PaintColor?) {
        if (color == null) return
        currentPaint.color = ContextCompat.getColor(context, color.colorRes)
    }
}
