package woowacourse.paint.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.brush.Brush
import woowacourse.paint.model.brush.Eraser
import woowacourse.paint.model.brush.Pen
import woowacourse.paint.model.palettecolor.PaletteColor

class FreeDrawView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val previousDrawings: MutableList<Pair<Path, Paint>> = mutableListOf()
    private var brush: Brush = Pen().apply {
        updateStyle(Paint().apply { color = Color.RED })
    }
    private var eraseMode: Boolean = false

    init {
        brush.updateStyle(brush.copyPaint())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        previousDrawings.forEach { (path, paint) ->
            canvas.drawPath(path, paint)
        }
        if (!eraseMode) {
            canvas.drawPath(brush.previewDraw.first, brush.previewDraw.second)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val cursorX = event.x
        val cursorY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (eraseMode) {
                    Eraser.eraseBrush(cursorX, cursorY, previousDrawings)
                    invalidate()
                } else {
                    brush.onActionDown(cursorX, cursorY) {
                        previousDrawings.add(it)
                        invalidate()
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (!eraseMode) {
                    brush.onActionMove(cursorX, cursorY) {
                        invalidate()
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                if (!eraseMode) {
                    brush.onActionUp(cursorX, cursorY) {
                        previousDrawings.add(it)
                        invalidate()
                    }
                }
            }

            else -> super.onTouchEvent(event)
        }

        return true
    }

    fun updateColor(color: PaletteColor) {
        brush.updateColor(context.getColor(color.resourceId))
    }

    fun updateThickness(thickness: Float) {
        brush.updateThickness(thickness)
    }

    fun setBrushType(brush: Brush) {
        val paint = this.brush.copyPaint()
        this.brush = brush
        this.brush.updateStyle(paint)
        eraseMode = false
    }

    fun setEraseMode() {
        eraseMode = true
    }
}
