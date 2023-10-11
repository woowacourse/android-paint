package woowacourse.paint.ui.glocanvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.ui.glocanvas.drawing.Drawings

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings: Drawings = Drawings()
    private val savedDrawings: Drawings = Drawings()
    private lateinit var palette: Palette

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    fun setupPalette(palette: Palette) {
        this.palette = palette
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.items.forEach {
            it.onDraw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val paint = palette.getPaint()
            val drawing = palette.drawingTool.createDrawing(paint, this::invalidate)
            drawings.addLast(drawing)
            savedDrawings.clear()
        }
        return drawings.getLastDrawing().onTouchEvent(event)
    }

    fun goToPreviousDrawing() {
        val drawing = drawings.removeLast()
        drawing?.let { savedDrawings.addFirst(drawing) }
        invalidate()
    }

    fun goToNextDrawing() {
        val drawing = savedDrawings.removeFirst()
        drawing?.let { drawings.addLast(drawing) }
        invalidate()
    }

    fun setNewCanvas() {
        drawings.clear()
        savedDrawings.clear()
        invalidate()
    }
}
