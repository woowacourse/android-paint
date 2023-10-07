package woowacourse.paint.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val painterHistory = PathPainterHistory()
    private var pathPainter = PathPainter()
    private var paletteMode: PaletteMode = PaletteMode.BRUSH

    fun setPaletteColor(paletteColor: PaletteColor) {
        pathPainter = pathPainter.setPaintColor(paletteColor)
        painterHistory.add(pathPainter)
    }

    fun setPaintThickness(painterThickness: Float) {
        pathPainter = pathPainter.setThickness(painterThickness)
        painterHistory.add(pathPainter)
    }

    fun setPaletteShape(paletteShape: PaletteShape) {
    }

    fun changePaletteMode(paletteMode: PaletteMode) {
        this.paletteMode = paletteMode
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        painterHistory.drawPath(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> pathPainter.dotTo(pointX, pointY)
            MotionEvent.ACTION_MOVE -> pathPainter.drawLine(pointX, pointY)
        }
        invalidate()
        return true
    }
}
