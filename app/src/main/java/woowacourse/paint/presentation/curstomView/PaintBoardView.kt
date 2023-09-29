package woowacourse.paint.presentation.curstomView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintBoardView(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val strokes: MutableList<Stroke> = mutableListOf()
    private val paint: Paint = Paint()
    private val touchEventListeners: MutableList<TouchEventListener> = mutableListOf()

    init {
        setUpView()
        setUpPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        strokes.forEach { canvas.drawPath(it.path, it.paint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> strokes.add(
                Stroke(Path().apply { moveTo(pointX, pointY) }, Paint(paint)),
            )

            MotionEvent.ACTION_MOVE -> strokes.last().path.lineTo(pointX, pointY)

            else -> super.onTouchEvent(event)
        }
        touchEventListeners.forEach { it.onTouch() }
        invalidate()
        return true
    }

    fun setStrokeColor(colorCode: Int) {
        paint.color = colorCode
    }

    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun addTouchEventListener(listener: TouchEventListener) {
        touchEventListeners.add(listener)
    }

    fun reset() {
        strokes.clear()
        invalidate()
    }

    private fun setUpView() {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    private fun setUpPaint() {
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
    }

    fun interface TouchEventListener {
        fun onTouch()
    }

    private class Stroke(val path: Path, val paint: Paint)
}
