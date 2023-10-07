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

    fun setPaletteColor(paletteColor: PaletteColor) {
        painterHistory.setPaletteColor(paletteColor)
    }

    fun setPaintThickness(painterThickness: Float) {
        painterHistory.setPaintThickness(painterThickness)
    }

    fun setPaletteShape(paletteShape: PaletteShape) {
        painterHistory.setPaletteShape(paletteShape)
    }

    fun changePaletteMode(paletteMode: PaletteMode) {
        painterHistory.changePaletteMode(paletteMode)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        painterHistory.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> painterHistory.onActionDown(pointX, pointY)
            MotionEvent.ACTION_MOVE -> painterHistory.onActionMove(pointX, pointY)
            MotionEvent.ACTION_UP -> painterHistory.onActionUp(pointX, pointY)
        }
        invalidate()
        return true
    }
}
