package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.painting.Line
import woowacourse.paint.painting.PaintingType

class PaintingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val drawer: Drawer = Drawer(
        initPresentPainting = Line(
            path = Path(),
            paintColor = context.getColor(PaintingColor.RED.colorRes),
            paintWidth = INIT_STROKE_WIDTH,
        ),
    )

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawer.presentPainting.onActionDown(pointX, pointY)

            MotionEvent.ACTION_MOVE -> drawer.presentPainting.onActionMove(pointX, pointY)

            MotionEvent.ACTION_UP -> {
                drawer.startNewPainting()
                return true
            }
        }

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawer.drawOnCanvas(canvas)
    }

    fun changePaintColor(colorRes: Int) {
        drawer.presentPainting.changeColor(context.getColor(colorRes))
    }

    fun changePaintWidth(width: Float) {
        drawer.presentPainting.changeWidth(width)
    }

    fun setPaintingType(paintingType: PaintingType) {
        drawer.setPaintingType(paintingType)
    }

    fun undo() {
        drawer.removePreviousPainting()
        invalidate()
    }

    companion object {
        private const val INIT_STROKE_WIDTH = 0f
    }
}
