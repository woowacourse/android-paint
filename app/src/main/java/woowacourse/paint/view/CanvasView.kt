package woowacourse.paint.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val path = Path()
    private val paint = Paint().softPainter()

    fun setPaintColor(paletteColor: PaletteColor) {
        paint.color = paletteColor.color
    }

    fun setPaintThickness(painterThickness: Float) {
        paint.strokeWidth = painterThickness
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(pointX, pointY)
            MotionEvent.ACTION_MOVE -> path.lineTo(pointX, pointY)
        }
        invalidate()
        return true
    }

    private fun Paint.softPainter(
        paletteColor: PaletteColor = PaletteColor.RED,
    ): Paint = apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        color = paletteColor.color
    }
}
