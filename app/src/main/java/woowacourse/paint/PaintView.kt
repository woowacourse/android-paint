package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Line
import woowacourse.paint.model.PaletteColor

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val lines: MutableList<Line> = mutableListOf()
    private val currentLine get() = lines[lines.lastIndex]

    var penSize: Float = 5f
    var penColor: Int = Color.parseColor(PaletteColor.BLUE.hexCode)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }

    private fun drawCanvas(canvas: Canvas) {
        lines.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> penDown(event)
            MotionEvent.ACTION_MOVE -> penMove(event)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    private fun penDown(event: MotionEvent) {
        addLine()
        currentLine.path.moveTo(event.x, event.y)
        penMove(event)
    }

    private fun penMove(event: MotionEvent) {
        val pointX: Float = event.x
        val pointY: Float = event.y
        currentLine.path.lineTo(pointX, pointY)
    }

    private fun addLine() {
        val paint = Paint().apply {
            color = penColor
            style = Paint.Style.STROKE
            strokeWidth = penSize
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
        lines.add(Line(Path(), paint))
    }
}
