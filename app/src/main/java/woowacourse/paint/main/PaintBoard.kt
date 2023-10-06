package woowacourse.paint.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.model.BrushSize
import woowacourse.paint.model.DrawMode
import woowacourse.paint.model.DrawableHistory
import woowacourse.paint.model.PaintColor
import woowacourse.paint.model.drawable.DrawableElement
import woowacourse.paint.model.drawable.DrawablePath
import woowacourse.paint.model.drawable.DrawableSquare

class PaintBoard constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val pathHistory = DrawableHistory()
    private var drawMode = DrawMode.DEFAULT_MODE
    private var currentDraw: DrawableElement = DrawablePath.from()

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
        drawMode = mode
        currentDraw = when (mode) {
            DrawMode.BRUSH -> DrawablePath.from(currentDraw.paint)
            DrawMode.SQUARE -> DrawableSquare.from(currentDraw.paint)
            // DrawMode.CIRCLE -> currentDraw = DrawableCircle.DEFAULT
            else -> throw IllegalArgumentException("존재하지 않는 DrawMode입니다: $mode")
        }
    }

    fun setBrushSize(size: BrushSize) {
        if (currentDraw is DrawablePath) {
            currentDraw = (currentDraw as DrawablePath).changeBrushSize(size)
        }
    }

    fun setBrushColor(color: PaintColor) {
        currentDraw =
            currentDraw.changePaintColor(ContextCompat.getColor(context, color.colorRes))
    }
}
