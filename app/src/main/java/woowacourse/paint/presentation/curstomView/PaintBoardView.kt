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
    attrs: AttributeSet?,
) : View(context, attrs) {

    private val path: Path = Path()
    private val paint: Paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    fun setStrokeColor(colorCode: Int) {
        paint.color = colorCode
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.addOval(
                pointX,
                pointY,
                pointX + OVAL_SIZE,
                pointY + OVAL_SIZE,
                Path.Direction.CW,
            )

            MotionEvent.ACTION_MOVE -> path.addOval(
                pointX,
                pointY,
                pointX + OVAL_SIZE,
                pointY + OVAL_SIZE,
                Path.Direction.CW,
            )

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    companion object {
        private const val OVAL_SIZE = 50
    }
}
