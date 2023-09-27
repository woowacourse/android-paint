package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.View

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint: Paint = Paint()
    private val path: Path = Path()

    init {
        setupPaintSetting()
    }

    private fun setupPaintSetting() {
        paint.apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = 20F
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                path.lineTo(event.x, event.y)
            }

            ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }
}
