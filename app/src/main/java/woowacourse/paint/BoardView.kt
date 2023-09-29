package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint()

    fun changeColor(color: ColorPalette) {
        when (color) {
            ColorPalette.RED -> paint.color = ContextCompat.getColor(context, R.color.red)
            ColorPalette.ORANGE -> paint.color = ContextCompat.getColor(context, R.color.orange)
            ColorPalette.YELLOW -> paint.color = ContextCompat.getColor(context, R.color.yellow)
            ColorPalette.GREEN -> paint.color = ContextCompat.getColor(context, R.color.green)
            ColorPalette.BLUE -> paint.color = ContextCompat.getColor(context, R.color.blue)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
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
        }
        invalidate()
        return true
    }
}
