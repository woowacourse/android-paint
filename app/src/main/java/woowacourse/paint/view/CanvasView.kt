package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.MyColor
import woowacourse.paint.model.Sketch
import woowacourse.paint.model.circle.Center
import woowacourse.paint.model.circle.Circle
import woowacourse.paint.model.line.Line
import woowacourse.paint.model.rectangle.Rectangle
import woowacourse.paint.model.rectangle.RectangleVertex
import woowacourse.paint.util.calculateDistance

class CanvasView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    val currentPaint = Paint()
    private val sketches = mutableListOf<Sketch>()
    private var currentPath = Path()
    private var currentRectangleVertex: RectangleVertex = RectangleVertex()
    private var currentCenter: Center = Center()
    private var currentRadius: Float = 0f
    private var brushType: BrushType = BrushType.PEN

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        initPaint()
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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (sketch in sketches) {
            sketch.draw(canvas)
        }
        when (brushType) {
            BrushType.PEN -> canvas.drawPath(currentPath, currentPaint)

            BrushType.RECTANGLE ->
                canvas.drawRect(
                    currentRectangleVertex.startX,
                    currentRectangleVertex.startY,
                    currentRectangleVertex.endX,
                    currentRectangleVertex.endY,
                    currentPaint,
                )

            BrushType.CIRCLE ->
                canvas.drawCircle(
                    currentCenter.x,
                    currentCenter.y,
                    currentRadius,
                    currentPaint,
                )

            BrushType.ERASER -> {}
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (brushType) {
            BrushType.PEN -> onTouchLineEvent(event)
            BrushType.RECTANGLE -> onTouchRectangleEvent(event)
            BrushType.CIRCLE -> onTouchCircleEvent(event)
            BrushType.ERASER -> onTouchEraserEvent(event)
        }
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
                currentRectangleVertex = RectangleVertex()
                currentRectangleVertex.changeVertex(
                    startX = event.x,
                    startY = event.y,
                    endX = event.x,
                    endY = event.y,
                )
            }

            MotionEvent.ACTION_MOVE -> {
                currentRectangleVertex.changeVertex(endX = event.x, endY = event.y)
            }

            MotionEvent.ACTION_UP -> {
                val currentRectangle =
                    Rectangle(currentRectangleVertex, currentPaint.color, currentPaint.strokeWidth)
                sketches.add(currentRectangle)
            }
        }
    }

    private fun onTouchCircleEvent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentCenter = Center()
                currentCenter.changeProperty(event.x, event.y)
                currentRadius = 0f
            }

            MotionEvent.ACTION_MOVE -> {
                currentRadius =
                    calculateDistance(currentCenter.x, currentCenter.y, event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                val currentCircle =
                    Circle(
                        currentCenter,
                        currentRadius,
                        currentPaint.color,
                        currentPaint.strokeWidth,
                    )
                sketches.add(currentCircle)
            }
        }
    }

    private fun onTouchEraserEvent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val haveToRemove = mutableListOf<Sketch>()
                sketches.forEach { sketch ->
                    if (sketch.isTouched(event.x, event.y)) haveToRemove.add(sketch)
                }
                sketches.removeAll(haveToRemove)
            }
        }
    }

    fun changeBrushType(brushType: BrushType) {
        this.brushType = brushType
    }
}
