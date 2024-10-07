package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.Drawing

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings = mutableListOf<Drawing>(Drawing())
    private var startX = 0f
    private var startY = 0f

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (drawing in drawings) {
            canvas.drawPath(drawing.path, drawing.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        val drawing = currentDrawing()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = pointX
                startY = pointY
                drawing.path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                drawing.move(
                    startX = startX,
                    startY = startY,
                    endX = pointX,
                    endY = pointY,
                )
            }

            MotionEvent.ACTION_UP -> {
                drawing.move(
                    startX = startX,
                    startY = startY,
                    endX = pointX,
                    endY = pointY,
                )

                val newDrawing =
                    drawing.copy(
                        path = Path(),
                        paint = Paint(drawing.paint),
                    )
                drawings.add(newDrawing)
            }

            else -> return false
        }

        invalidate()
        return true
    }

    private fun currentDrawing(): Drawing = drawings.last()

    fun setBrushWidth(width: Float) {
        currentDrawing().updateBrush { changeWidth(width) }
    }

    fun setBrushColor(color: Int) {
        currentDrawing().updateBrush { changeColor(color) }
    }

    fun setBrushType(brushType: BrushType) {
        currentDrawing().updateBrush { changeBrushType(brushType) }
        if (brushType == BrushType.ERASER) {
            currentDrawing().setEraseMode(true)
        } else {
            currentDrawing().setEraseMode(false)
        }
    }
}
