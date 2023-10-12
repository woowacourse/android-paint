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

class PaintingPaper(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paintings = Paintings()
    private var brush: BrushType = Line()

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
                brush.startDrawing(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE -> {
                brush.moveDrawing(pointX, pointY)
            }
            MotionEvent.ACTION_UP -> {
                doActionUp(pointX, pointY)
            }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setupBrush(otherBrush: BrushType) {
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

    private fun doActionUp(pointX: Float, pointY: Float) {
        val currentPaint = brush.paint
        val currentPath = brush.path
        when (brush.type) {
            CIRCLE -> {
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupBrush(Circle())
            }

            RECTANGLE -> {
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupBrush(Rectangle())
            }

            LINE -> {
                (brush as Line).doActionUp(pointX, pointY)
                paintings.storePainting(Painting(currentPaint, currentPath))
                setupBrush(Line())
            }

            ERASER -> {
                (brush as Eraser).doActionUp(pointX, pointY)
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
