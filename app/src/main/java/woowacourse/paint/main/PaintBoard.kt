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
import woowacourse.paint.model.DrawMode
import woowacourse.paint.model.DrawablePath
import woowacourse.paint.model.DrawablePathHistory
import woowacourse.paint.model.PaintColor

class PaintBoard constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val pathHistory = DrawablePathHistory()
    private var drawMode = DrawMode.DEFAULT_MODE
    private var currentDraw: DrawablePath = DrawablePath(Path(), Paint())

    init {
        setDefaultPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        pathHistory.drawAll(canvas)
        currentDraw.drawCurrentPath(canvas)
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
        currentDraw.initPaint()
    }

    private fun startDrawing(event: MotionEvent) {
        currentDraw = currentDraw.startDrawing(event.x, event.y)
    }

    private fun moveDrawing(event: MotionEvent) {
        currentDraw.keepDrawing(event.x, event.y)
    }

    private fun endDrawing() {
        pathHistory.add(currentDraw)
    }

    fun setDrawMode(mode: DrawMode) {
        drawMode = mode
    }

    fun setBrushSize(size: BrushSize) {
        currentDraw = currentDraw.changeBrushSize(size)
    }

    fun setBrushColor(color: PaintColor) {
        currentDraw = currentDraw.changePaintColor(ContextCompat.getColor(context, color.colorRes))
    }
}
