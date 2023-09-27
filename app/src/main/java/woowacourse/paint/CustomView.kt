package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class CustomView constructor(context: Context, attr: AttributeSet) : View(context, attr) {
    private val path = Path()
    private val paint = Paint()
    private lateinit var canvas: Canvas
    private lateinit var bitmap: Bitmap
    private var xBefore: Float = 0f
    private var yBefore: Float = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.drawPath(path, paint)
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startDrawing(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                keepDrawing(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                finishDrawing()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun startDrawing(x: Float, y: Float) {
        path.reset()
        path.moveTo(x, y)
        xBefore = x
        yBefore = y
    }

    private fun keepDrawing(x: Float, y: Float) {
        if (abs(x - xBefore) >= MOVE_THRESHOLD || abs(y - yBefore) >= MOVE_THRESHOLD) {
            path.quadTo(xBefore, yBefore, (x + xBefore) / 2, (y + yBefore) / 2)
            xBefore = x
            yBefore = y
        }
    }

    private fun finishDrawing() {
        path.lineTo(xBefore, yBefore)
        canvas.drawPath(path, paint)
        path.reset()
    }

    private fun setupPaint() {
        setColor(context.getColor(R.color.red))
        setStrokeWidth(10f)
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
    }

    companion object {
        private const val MOVE_THRESHOLD = 5
    }
}
