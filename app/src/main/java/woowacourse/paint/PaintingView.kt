package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var painting: Painting =
        Painting(
            path = Path(),
            paintColor = context.getColor(R.color.red),
            paintWidth = 0f,
        )

    private val previousPaintings: Paintings = Paintings()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                painting.movePath(pointX, pointY)
                painting.linePath(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> painting.linePath(pointX, pointY)

            MotionEvent.ACTION_UP -> {
                previousPaintings.add(painting)
                painting = painting.copy(path = Path())
            }
        }

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        previousPaintings.drawOnCanvas(canvas)
        painting.drawOnCanvas(canvas)
    }

    fun changePaintColor(color: Int) {
        painting.changeColor(color = color)
    }

    fun changePaintWidth(width: Float) {
        painting.changeWidth(width)
    }
}
