package woowacourse.paint.presentation.curstomView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintBoardView(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val path: Path = Path()
    private val paint: Paint = Paint()
    private val touchEventListeners: MutableList<TouchEventListener> = mutableListOf()

    init {
        setUpView()
        setUpPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(pointX, pointY)

            MotionEvent.ACTION_MOVE -> path.lineTo(pointX, pointY)

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

    private fun setUpView() {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    private fun setUpPaint() {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
    }

    fun interface TouchEventListener {
        fun onTouch()
    }
}
