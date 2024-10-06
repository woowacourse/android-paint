package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.BrushMode
import woowacourse.paint.model.Drawing

class CustomView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    private val drawings: MutableList<Drawing> = mutableListOf(Drawing())
    private val stackHistory = mutableListOf<Drawing>()

    private var startX: Float = 0f
    private var startY: Float = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawAllPaths(canvas)
    }

    private fun drawAllPaths(canvas: Canvas) {
        for (drawing in drawings) {
            drawing.draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        val drawing = drawings.last()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = pointX
                startY = pointY

                drawing.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                drawing.saveMovement(startX, startY, pointX, pointY, false)
            }

            MotionEvent.ACTION_UP -> {
                drawings.add(newDrawing())
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun currentDrawing(): Drawing = drawings.last()

    private fun setupPaint() {
        currentDrawing().changeColor(Color.RED)
        currentDrawing().changePaintStyle(Paint.Style.STROKE)
        currentDrawing().changeStrokeWidth(5f)
    }

    fun changeColor(color: Int) {
        currentDrawing().changeColor(color)
    }

    fun changeStrokeWidth(width: Float) {
        currentDrawing().changeStrokeWidth(width)
    }

    fun changeBrushMode(brushMode: BrushMode) {
        if (currentDrawing().brushMode == BrushMode.ERASER && brushMode != BrushMode.ERASER) {
            currentDrawing().changeColor(Color.RED)
        }
        currentDrawing().brushMode = brushMode
    }

    private fun newDrawing(): Drawing =
        Drawing(
            paint = Paint(currentDrawing().paint),
            brushMode = currentDrawing().brushMode,
        )

    fun undo() {
        if (drawings.size > 1) {
            stackHistory.add(drawings.removeAt(drawings.size - 2))
            invalidate()
        }
    }

    fun redo() {
        if (stackHistory.isNotEmpty()) {
            drawings.add(stackHistory.removeLast())
            invalidate()
        }
    }

    fun clear() {
        val newDrawing = newDrawing()
        stackHistory.clear()
        drawings.clear()
        drawings.add(newDrawing)
        invalidate()
    }
}
