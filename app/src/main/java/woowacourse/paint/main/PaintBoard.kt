package woowacourse.paint.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.model.BrushSize
import woowacourse.paint.model.DrawMode
import woowacourse.paint.model.DrawableHistory
import woowacourse.paint.model.PaintColor
import woowacourse.paint.model.drawable.DrawableCircle
import woowacourse.paint.model.drawable.DrawableElement
import woowacourse.paint.model.drawable.DrawableEraser
import woowacourse.paint.model.drawable.DrawablePath
import woowacourse.paint.model.drawable.DrawableSquare

class PaintBoard constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val pathHistory = DrawableHistory()
    private var currentDraw: DrawableElement = DrawablePath(paint = Paint())

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        pathHistory.drawAll(canvas)
        currentDraw.drawCurrent(canvas)
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
        val newPaint = Paint().apply {
            color = currentDraw.paint.color
            strokeWidth = currentDraw.paint.strokeWidth
        }
        currentDraw = when (mode) {
            DrawMode.BRUSH -> DrawablePath(paint = newPaint)
            DrawMode.SQUARE -> DrawableSquare(paint = newPaint)
            DrawMode.CIRCLE -> DrawableCircle(paint = newPaint)
            DrawMode.ERASER -> DrawableEraser(paint = newPaint)
        }
    }

    fun setBrushSize(size: BrushSize) {
        if (currentDraw is DrawablePath) {
            currentDraw = (currentDraw as DrawablePath).changeBrushSize(size)
        } else if (currentDraw is DrawableEraser) {
            currentDraw = (currentDraw as DrawableEraser).changeBrushSize(size)
        }
    }

    fun setBrushColor(color: PaintColor) {
        currentDraw = currentDraw.changePaintColor(ContextCompat.getColor(context, color.colorRes))
    }
}
