package woowacourse.paint.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val painterHistory = PainterHistory()
    private var currentPainter: Painter = BrushPainter()

    fun setPaletteColor(paletteColor: PaletteColor) {
        currentPainter = currentPainter.setPaletteColor(paletteColor)
        painterHistory.add(currentPainter)
    }

    fun setPaintThickness(painterThickness: Float) {
        currentPainter = currentPainter.setThickness(painterThickness)
        painterHistory.add(currentPainter)
    }

    fun setPaletteShape(paletteShape: PaletteShape) {
    }

    fun changePaletteMode(paletteMode: PaletteMode) {
        currentPainter = currentPainter.changePainter(paletteMode)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        painterHistory.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> currentPainter.onTouchDown(pointX, pointY)
            MotionEvent.ACTION_MOVE -> currentPainter.onTouchMove(pointX, pointY)
        }
        invalidate()
        return true
    }
}
