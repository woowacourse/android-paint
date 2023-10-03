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

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint()
    private lateinit var storedCanvas: Canvas
    private lateinit var bitmap: Bitmap

    fun changeColor(color: ColorPalette) {
        when (color) {
            ColorPalette.RED -> paint.color = ContextCompat.getColor(context, R.color.red)
            ColorPalette.ORANGE -> paint.color = ContextCompat.getColor(context, R.color.orange)
            ColorPalette.YELLOW -> paint.color = ContextCompat.getColor(context, R.color.yellow)
            ColorPalette.GREEN -> paint.color = ContextCompat.getColor(context, R.color.green)
            ColorPalette.BLUE -> paint.color = ContextCompat.getColor(context, R.color.blue)
        }
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

        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.isAntiAlias = true
        canvas.drawBitmap(bitmap, 0f, 0f, null)
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
                storedCanvas.drawPath(path, paint)
                path.reset()
            }
        }
        invalidate()
        return true
    }
}
