package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.domain.BrushType.CIRCLE
import com.example.domain.BrushType.ERASER
import com.example.domain.BrushType.LINE
import com.example.domain.BrushType.RECTANGLE
import woowacourse.paint.Painting
import woowacourse.paint.Paintings
import woowacourse.paint.ui.brushtype.BrushType
import woowacourse.paint.ui.brushtype.Circle
import woowacourse.paint.ui.brushtype.Eraser
import woowacourse.paint.ui.brushtype.Line
import woowacourse.paint.ui.brushtype.Rectangle

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paintings = Paintings()
    private var brush: BrushType = Line()

    init {
        brush.setupPaint()
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintings.painting.forEach { painting ->
            canvas.drawPath(painting.path, painting.paint)
        }
        canvas.drawPath(brush.getPath(), brush.getPaint())
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                brush.doActionDown(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE -> {
                brush.doActionMove(pointX, pointY)
            }
            MotionEvent.ACTION_UP -> {
                doActionUp(pointX, pointY)
            }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setupPen() {
        val paint = brush.getPaint()
        brush = Line()
        brush.setupPaint(paint.strokeWidth, paint.color)
    }

    fun setupCircle() {
        val paint = brush.getPaint()
        brush = Circle()
        brush.setupPaint(paint.strokeWidth, paint.color)
    }

    fun setupRectangle() {
        val paint = brush.getPaint()
        brush = Rectangle()
        brush.setupPaint(paint.strokeWidth, paint.color)
    }

    fun setupEraser() {
        val paint = brush.getPaint()
        brush = Eraser()
        brush.setupPaint(paint.strokeWidth, paint.color)
    }

    fun setMyStrokeWidth(width: Float) {
        brush.setStrokeWidth(width)
    }

    fun setMyStrokeColor(color: Int) {
        brush.setStrokeColor(color)
    }

    private fun doActionUp(pointX: Float, pointY: Float) {
        val currentPaint = brush.getPaint()
        val currentPath = brush.getPath()
        when (brush.type) {
            CIRCLE -> {
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupCircle()
            }

            RECTANGLE -> {
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupRectangle()
            }

            LINE -> {
                (brush as Line).doActionUp(pointX, pointY)
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupPen()
            }

            ERASER -> {
                (brush as Eraser).doActionUp(pointX, pointY)
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupEraser()
            }
        }
    }

    fun resetCanvas() {
        paintings.removeAllPaintings()
        invalidate()
    }
}
