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
import woowacourse.paint.model.drawable.DrawableElement
import woowacourse.paint.model.drawable.path.DrawableEraser
import woowacourse.paint.model.drawable.path.DrawableLine
import woowacourse.paint.model.drawable.path.DrawablePath
import woowacourse.paint.model.drawable.shape.DrawableCircle
import woowacourse.paint.model.drawable.shape.DrawableSquare

class PaintBoard constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val pathHistory = DrawableHistory()
    private var currentDraw: DrawableElement = DrawableLine(paint = Paint())

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        pathHistory.drawAll(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startDrawing(event)
            MotionEvent.ACTION_MOVE -> moveDrawing(event)
            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun startDrawing(event: MotionEvent) {
        currentDraw = currentDraw.startDrawing(event.x, event.y)
        pathHistory.add(currentDraw)
    }

    private fun moveDrawing(event: MotionEvent) {
        currentDraw.keepDrawing(event.x, event.y)
    }

    fun setDrawMode(mode: DrawMode) {
        val newPaint = Paint().apply {
            color = currentDraw.paint.color
            strokeWidth = currentDraw.paint.strokeWidth
        }
        currentDraw = when (mode) {
            DrawMode.BRUSH -> DrawableLine(paint = newPaint)
            DrawMode.SQUARE -> DrawableSquare(paint = newPaint)
            DrawMode.CIRCLE -> DrawableCircle(paint = newPaint)
            DrawMode.ERASER -> DrawableEraser(paint = newPaint)
        }
    }

    fun setBrushSize(size: BrushSize) {
        if (currentDraw is DrawablePath) {
            currentDraw = (currentDraw as DrawablePath).changeBrushSize(size)
        }
    }

    fun setBrushColor(color: PaintColor) {
        currentDraw = currentDraw.changePaintColor(ContextCompat.getColor(context, color.colorRes))
    }

    fun undo() {
        pathHistory.undo()
        invalidate()
    }

    fun redo() {
        pathHistory.redo()
        invalidate()
    }

    fun deleteAll() {
        pathHistory.clear()
        invalidate()
    }
}
