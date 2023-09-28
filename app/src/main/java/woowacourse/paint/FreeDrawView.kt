package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class FreeDrawView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val path = Path()
    private val paint = Paint()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                true
            }

            MotionEvent.ACTION_MOVE -> {
                true
            }

            else -> {
                false
            }
        }
    }

    fun updateColor(color: Int) {
        paint.color = color
    }

    fun updateThickness() {
    }
}
