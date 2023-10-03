package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView constructor(context: Context, attr: AttributeSet) : View(context, attr) {

    private val brush by lazy {
        Brush().also {
            it.setColor(context.getColor(woowacourse.paint.Color.values().first().id))
        }
    }
    private lateinit var canvas: Canvas
    private lateinit var bitmap: Bitmap

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, 0f, 0f, brush.paint)
        canvas.drawPath(brush.path, brush.paint)
    }

    fun setColor(color: Color) {
        brush.setColor(context.getColor(color.id))
    }

    fun setStrokeWidth(width: Float) {
        brush.setStrokeWidth(width)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                brush.startDrawing(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                brush.keepDrawing(pointX, pointY)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                finishDrawing()
                invalidate()
            }

            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun finishDrawing() {
        brush.lineToPrevPoint()
        canvas.drawPath(brush.path, brush.paint)
    }
}
