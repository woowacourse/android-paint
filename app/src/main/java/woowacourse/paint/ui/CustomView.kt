package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.Painting
import woowacourse.paint.Paintings

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var path = Path()
    private var paint = Paint()

    private val paintings = Paintings(mutableListOf())

    init {
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintings.painting.forEach { painting ->
            canvas.drawPath(painting.path, painting.paint)
        }
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
            }
            MotionEvent.ACTION_UP -> {
                path.lineTo(pointX, pointY)
                paintings.storePainting(Painting(paint, path))
                setupPaint(paint.strokeWidth, paint.color)
            }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun setupPaint(width: Float = 0F, color: Int = Color.BLACK) {
        path = Path()
        paint = Paint()

        paint.isAntiAlias = true
        paint.color = color
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = width
    }

    fun setMyStrokeWidth(width: Float) {
        paint.strokeWidth = width
        invalidate()
    }

    fun setMyStrokeColor(color: Int) {
        paint.color = color
        invalidate()
    }
}
