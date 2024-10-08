package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import kotlin.math.sqrt

class PaintBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawShapes = mutableListOf<DrawShape>()
    private val paint = Paint()
    private var currentShapeType = ShapeType.FREEHAND
    private var startX = 0f
    private var startY = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setLayerType(LAYER_TYPE_HARDWARE, null)
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawShapes.forEach { shape ->
            paint.strokeWidth = shape.strokeWidth
            paint.color = shape.color
            paint.style = shape.style
            when (shape) {
                is DrawShape.Line -> canvas.drawPath(shape.path, paint)
                is DrawShape.Rectangle -> canvas.drawRect(shape.rect, paint)
                is DrawShape.Circle ->
                    canvas.drawCircle(
                        shape.centerX,
                        shape.centerY,
                        shape.radius,
                        paint,
                    )
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                when (currentShapeType) {
                    ShapeType.FREEHAND, ShapeType.ERASER -> {
                        val path = Path()
                        path.moveTo(startX, startY)
                        drawShapes.add(DrawShape.Line(path, paint.strokeWidth, paint.color, Paint.Style.STROKE))
                    }

                    ShapeType.RECTANGLE -> {
                        val path = Path()
                        path.moveTo(startX, startY)
                        drawShapes.add(
                            DrawShape.Rectangle(
                                RectF(startX, startY, event.x, event.y),
                                paint.strokeWidth,
                                paint.color,
                                Paint.Style.FILL
                            ),
                        )
                    }

                    ShapeType.CIRCLE -> {
                        val path = Path()
                        val radius = calculateRadius(startX, startY, event.x, event.y)

                        path.moveTo(startX, startY)
                        drawShapes.add(
                            DrawShape.Circle(
                                startX,
                                startY,
                                radius,
                                paint.strokeWidth,
                                paint.color,
                                Paint.Style.FILL
                            ),
                        )
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                when (currentShapeType) {
                    ShapeType.FREEHAND, ShapeType.ERASER -> {
                        drawShapes.lastOrNull()?.let {
                            if (it is DrawShape.Line) {
                                it.path.lineTo(event.x, event.y)
                            }
                        }
                    }

                    ShapeType.RECTANGLE -> {
                        if (drawShapes.isNotEmpty() && drawShapes.last() !is DrawShape.Line) {
                            drawShapes.removeLastOrNull()
                        }

                        drawShapes.add(
                            DrawShape.Rectangle(
                                RectF(startX, startY, event.x, event.y),
                                paint.strokeWidth,
                                paint.color,
                                Paint.Style.FILL
                            ),
                        )
                    }

                    ShapeType.CIRCLE -> {
                        if (drawShapes.isNotEmpty() && drawShapes.last() !is DrawShape.Line) {
                            drawShapes.removeLastOrNull()
                        }

                        val radius = calculateRadius(startX, startY, event.x, event.y)
                        drawShapes.add(
                            DrawShape.Circle(
                                startX,
                                startY,
                                radius,
                                paint.strokeWidth,
                                paint.color,
                                Paint.Style.FILL
                            ),
                        )
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                when (currentShapeType) {
                    ShapeType.FREEHAND, ShapeType.ERASER -> Unit

                    ShapeType.RECTANGLE -> {
                        val rect = RectF(startX, startY, event.x, event.y)
                        drawShapes.add(DrawShape.Rectangle(rect, paint.strokeWidth, paint.color))
                    }

                    ShapeType.CIRCLE -> {
                        val radius = calculateRadius(startX, startY, event.x, event.y)
                        drawShapes.add(
                            DrawShape.Circle(
                                startX,
                                startY,
                                radius,
                                paint.strokeWidth,
                                paint.color,
                            ),
                        )
                    }
                }
            }
        }
        invalidate()
        return true
    }

    fun setStrokeWidth(strokeWidth: Float) {
        paint.strokeWidth = strokeWidth
    }

    fun setColor(
        @ColorRes color: Int,
    ) {
        paint.color = context.getColor(color)
    }

    fun setShapeType(shapeType: ShapeType) {
        currentShapeType = shapeType

        if (shapeType == ShapeType.ERASER) {
            val uiMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            paint.color = if (uiMode == UI_MODE_NIGHT_YES) {
                context.getColor(R.color.black)
            } else {
                context.getColor(R.color.white)
            }
        } else {
            paint.color = context.getColor(R.color.red)
        }
    }

    private fun setupPaint() {
        paint.color = Color.RED
        paint.strokeWidth = 10f
    }

    private fun calculateRadius(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
    ): Float {
        return sqrt(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)).toDouble()).toFloat()
    }
}
