package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paintings: Paintings = Paintings(
        initPresentPainting = Painting(
            path = Path(),
            paintColor = context.getColor(PaintingColor.RED.colorRes),
            paintWidth = INIT_STROKE_WIDTH,
        ),
    )

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                paintings.presentPainting?.movePath(pointX, pointY)
                paintings.presentPainting?.linePath(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> paintings.presentPainting?.linePath(pointX, pointY)
            MotionEvent.ACTION_UP -> {
                paintings.startNewPainting()
                return true
            }
        }

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintings.drawOnCanvas(canvas)
    }

    fun changePaintColor(color: Int) {
        paintings.presentPainting?.changeColor(color)
    }

    fun changePaintWidth(width: Float) {
        paintings.presentPainting?.changeWidth(width)
    }

    companion object {
        private const val INIT_STROKE_WIDTH = 0f
    }
}
