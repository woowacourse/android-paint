package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Line
import woowacourse.paint.model.MyColor
import woowacourse.paint.model.Rectangle
import woowacourse.paint.model.Sketch
import woowacourse.paint.model.Vertexes

class CanvasView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    private val sketches = mutableListOf<Sketch>()
    private var currentPath = Path()
    private var currentVertexes: Vertexes = Vertexes()
    val currentPaint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        initPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (sketch in sketches) {
            sketch.draw(canvas)
        }
//        canvas.drawPath(currentPath, currentPaint)
        canvas.drawRect(
            currentVertexes.startX,
            currentVertexes.startY,
            currentVertexes.endX,
            currentVertexes.endY,
            currentPaint,
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
//        onTouchLineEvent(event)
        onTouchRectangleEvent(event)
        invalidate()
        return true
    }

    private fun onTouchLineEvent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path()
                currentPath.moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                sketches.add(Line(currentPath, currentPaint.color, currentPaint.strokeWidth))
            }

            else -> super.onTouchEvent(event)
        }
    }

    private fun onTouchRectangleEvent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentVertexes.changeVertex(event.x, event.y, event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                currentVertexes.changeVertex(endX = event.x, endY = event.y)
            }

            MotionEvent.ACTION_UP -> {
                currentVertexes.changeVertex(endX = event.x, endY = event.y)
                sketches.add(Rectangle(currentVertexes, currentPaint.color, currentPaint.strokeWidth))
            }
        }
    }

    private fun initPaint() {
        currentPaint.apply {
            color = MyColor.RED
            style = Paint.Style.STROKE
            strokeWidth = 10f
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }
}
