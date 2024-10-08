package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class DrawingView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    private val strokes = mutableListOf<Stroke>()
    private var currentPath: Path? = null
    private var currentPaint: Paint? = null
    private val undoneStrokes = mutableListOf<Stroke>()
    private var startX = START_POSITION
    private var startY = START_POSITION
    private var currentBrushType = BrushType.PEN
    private var currentColor = Color.BLACK
    private var currentStrokeWidth = INIT_STROKE_WIDTH
    private var lastX = 0f
    private var lastY = 0f
    private val touchTolerance = 4f

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                when (currentBrushType) {
                    BrushType.PEN, BrushType.ERASER -> {
                        currentPath =
                            Path().apply {
                                moveTo(x, y)
                            }
                        currentPaint = createPaint()
                        strokes.add(
                            Stroke(
                                path = currentPath,
                                paint = currentPaint!!,
                                brushType = currentBrushType,
                            ),
                        )
                    }

                    BrushType.RECTANGLE, BrushType.CIRCLE -> {
                        startX = x
                        startY = y
                        currentPaint = createPaint()
                        strokes.add(
                            Stroke(
                                path = null,
                                paint = currentPaint!!,
                                brushType = currentBrushType,
                                startX = startX,
                                startY = startY,
                                endX = startX,
                                endY = startY,
                            ),
                        )
                    }
                }
                lastX = x
                lastY = y
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                when (currentBrushType) {
                    BrushType.PEN, BrushType.ERASER -> {
                        currentPath?.lineTo(x, y)
                    }

                    BrushType.RECTANGLE, BrushType.CIRCLE -> {
                        val lastStroke = strokes.last()
                        strokes[strokes.size - 1] = lastStroke.copy(endX = x, endY = y)
                    }
                }
                if (abs(x - lastX) >= touchTolerance || abs(y - lastY) >= touchTolerance) {
                    lastX = x
                    lastY = y
                    invalidate()
                }
                return true
            }

            MotionEvent.ACTION_UP -> {
                currentPath = null
                currentPaint = null
                invalidate()
                return true
            }

            else -> return false
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (stroke in strokes) {
            when (stroke.brushType) {
                BrushType.PEN -> {
                    stroke.path?.let { canvas.drawPath(it, stroke.paint) }
                }

                BrushType.RECTANGLE -> {
                    canvas.drawRect(
                        stroke.startX,
                        stroke.startY,
                        stroke.endX,
                        stroke.endY,
                        stroke.paint,
                    )
                }

                BrushType.CIRCLE -> {
                    val radius =
                        Math
                            .hypot(
                                (stroke.endX - stroke.startX).toDouble(),
                                (stroke.endY - stroke.startY).toDouble(),
                            ).toFloat()
                    canvas.drawCircle(stroke.startX, stroke.startY, radius, stroke.paint)
                }

                BrushType.ERASER -> {
                    stroke.path?.let { canvas.drawPath(it, stroke.paint) }
                }
            }
        }
    }

    private fun createPaint(): Paint =
        Paint().apply {
            color =
                if (currentBrushType == BrushType.ERASER) {
                    Color.TRANSPARENT
                } else {
                    currentColor
                }
            style =
                when (currentBrushType) {
                    BrushType.PEN, BrushType.ERASER -> Paint.Style.STROKE
                    BrushType.RECTANGLE, BrushType.CIRCLE -> Paint.Style.FILL_AND_STROKE
                }
            strokeWidth = currentStrokeWidth
            isAntiAlias = true
            if (currentBrushType == BrushType.ERASER) {
                xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            }
        }

    fun updateBrushColor(color: Int) {
        currentColor = color
    }

    fun clearCanvas() {
        strokes.clear()
        invalidate()
    }

    fun undo() {
        if (strokes.isNotEmpty()) {
            val lastStroke = strokes.removeAt(strokes.size - 1)
            undoneStrokes.add(lastStroke)
            invalidate()
        }
    }

    fun redo() {
        if (undoneStrokes.isNotEmpty()) {
            val redoStroke = undoneStrokes.removeAt(undoneStrokes.size - 1)
            strokes.add(redoStroke)
            invalidate()
        }
    }

    fun updateBrushSize(size: Float) {
        currentStrokeWidth = size
    }

    fun setBrushType(brushType: BrushType) {
        currentBrushType = brushType
    }

    companion object {
        private const val INIT_STROKE_WIDTH = 10f
        private const val START_POSITION = 0f
    }
}
