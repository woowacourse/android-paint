package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.model.ColorPalette

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint()
    private lateinit var storedCanvas: Canvas
    private lateinit var bitmap: Bitmap

    fun changeColor(colorPalette: ColorPalette) {
        paint.color = ContextCompat.getColor(context, colorPalette.color)
    }

    fun setWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun erase() {
        bitmap.eraseColor(Color.TRANSPARENT)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        storedCanvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setDefaultPaint()
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        canvas.drawPath(path, paint)
    }

    private fun setDefaultPaint() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startDrawing(event)
            MotionEvent.ACTION_MOVE -> moveDrawing(event)
            MotionEvent.ACTION_UP -> endDrawing()
        }
        invalidate()
        return true
    }

    private fun startDrawing(event: MotionEvent) {
        path.moveTo(event.x, event.y)
        path.lineTo(event.x, event.y)
    }

    private fun moveDrawing(event: MotionEvent) {
        path.lineTo(event.x, event.y)
    }

    private fun endDrawing() {
        storedCanvas.drawPath(path, paint)
        path.reset()
    }
}
