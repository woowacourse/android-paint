package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.domain.Coordinate
import woowacourse.paint.ui.brushtype.Brush
import woowacourse.paint.ui.brushtype.Circle
import woowacourse.paint.ui.brushtype.Eraser
import woowacourse.paint.ui.brushtype.Line
import woowacourse.paint.ui.brushtype.Rectangle
import woowacourse.paint.ui.model.Painting
import woowacourse.paint.ui.model.Paintings

class PaintingPaper(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paintings = Paintings()
    private var brush: Brush = Line()

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintings.painting.forEach { painting ->
            canvas.drawPath(painting.path, painting.paint)
        }
        canvas.drawPath(brush.path, brush.paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                brush.startDrawing(Coordinate(pointX, pointY))
            }
            MotionEvent.ACTION_MOVE -> {
                brush.moveDrawing(Coordinate(pointX, pointY))
            }
            MotionEvent.ACTION_UP -> {
                doActionUp(pointX, pointY, brush)
            }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setupBrush(otherBrush: Brush) {
        val width = brush.paint.strokeWidth
        val color = brush.paint.color
        brush = otherBrush
        brush.apply {
            paint.strokeWidth = width
            paint.color = color
        }
    }

    fun setMyStrokeWidth(width: Float) {
        brush.paint.strokeWidth = width
    }

    fun setMyStrokeColor(color: Int) {
        brush.paint.color = color
    }

    private fun doActionUp(pointX: Float, pointY: Float, brush: Brush) {
        val currentPaint = this.brush.paint
        val currentPath = this.brush.path
        when (brush::class) {
            Circle::class -> {
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupBrush(Circle())
            }
            Rectangle::class -> {
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupBrush(Rectangle())
            }
            Line::class -> {
                (this.brush as Line).doActionUp(Coordinate(pointX, pointY))
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupBrush(Line())
            }
            Eraser::class -> {
                (this.brush as Eraser).doActionUp(Coordinate(pointX, pointY))
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupBrush(Eraser())
            }
        }
    }

    fun resetCanvas() {
        paintings.removeAllPaintings()
        invalidate()
    }
}
