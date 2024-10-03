package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.R
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.Stroke

class CanvasView(context: Context, attrs: AttributeSet) :
    View(context, attrs) {
    private val strokes = mutableListOf<Stroke>()
    private var currentBrushType = BrushType.PEN
    private var currentPath: Path? = null
    private val currentPaint: Paint =
        Paint().apply {
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    private val currentRect = RectF()

    private var startX: Float = 0f
    private var startY: Float = 0f
    private var endX: Float = 0f
    private var endY: Float = 0f

    init {
        initializePaint(context, attrs)
    }

    private fun initializePaint(context: Context, attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0,
            0,
        ).apply {
            initializePaintByResourceType()
        }
    }

    private fun TypedArray.initializePaintByResourceType() {
        try {
            currentPaint.color = getColor(R.styleable.CustomView_lineColor, DEFAULT_LINE_COLOR)
            currentPaint.strokeWidth =
                getDimension(R.styleable.CustomView_lineWidth, DEFAULT_LINE_WIDTH)
        } finally {
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (stroke in strokes) {
            canvas.drawPath(stroke.path, stroke.paint)
        }

        when (currentBrushType) {
            BrushType.PEN -> currentPath?.let { canvas.drawPath(it, currentPaint) }

            BrushType.RECTANGULAR -> {
                currentPath?.apply {
                    moveTo(startX, startY)
                    lineTo(endX, startY)
                    lineTo(endX, endY)
                    lineTo(startX, endY)
                    close()
                }?.let {
                    canvas.drawPath(it, currentPaint)
                }
            }

            BrushType.CIRCLE -> {
                val rect = currentRect.apply {
                    left = startX.coerceAtMost(endX)
                    top = startY.coerceAtMost(endY)
                    right = startX.coerceAtLeast(endX)
                    bottom = startY.coerceAtLeast(endY)
                }
                canvas.drawOval(rect, currentPaint)
            }

            BrushType.ERASER -> {

            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path().apply { moveTo(pointX, pointY) }
                startX = pointX
                startY = pointY
                endX = pointX
                endY = pointY
            }

            MotionEvent.ACTION_MOVE -> {
                when (currentBrushType) {
                    BrushType.PEN -> currentPath?.lineTo(pointX, pointY)
                    BrushType.RECTANGULAR, BrushType.CIRCLE -> {
                        endX = event.x
                        endY = event.y
                    }

                    BrushType.ERASER -> {

                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                when (currentBrushType) {
                    BrushType.PEN -> {
                        currentPath?.let {
                            strokes.add(Stroke(it, Paint(currentPaint)))
                        }
                        currentPath = null
                    }

                    BrushType.RECTANGULAR -> {
                        currentPath?.apply {
                            moveTo(startX, startY)
                            lineTo(endX, startY)
                            lineTo(endX, endY)
                            lineTo(startX, endY)
                            close()
                        }?.let {
                            strokes.add(Stroke(it, Paint(currentPaint)))
                        }
                    }

                    BrushType.CIRCLE -> {
                        val rect = currentRect.apply {
                            left = startX.coerceAtMost(endX)
                            top = startY.coerceAtMost(endY)
                            right = startX.coerceAtLeast(endX)
                            bottom = startY.coerceAtLeast(endY)
                        }
                        strokes.add(
                            Stroke(
                                currentPath?.apply { addOval(rect, Path.Direction.CW) }
                                    ?: error("CurrentPath does not exist."),
                                Paint(currentPaint)
                            )
                        )
                    }

                    BrushType.ERASER -> {

                    }
                }
            }

            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setLineColor(color: Int) {
        currentPaint.color = color
    }

    fun setLineWidth(width: Float) {
        currentPaint.strokeWidth = width
    }

    fun setBrushType(brushType: BrushType) {
        currentBrushType = brushType
        when (currentBrushType) {
            BrushType.PEN, BrushType.ERASER -> currentPaint.style = Paint.Style.STROKE
            BrushType.RECTANGULAR, BrushType.CIRCLE -> currentPaint.style = Paint.Style.FILL
        }
    }

    companion object {
        private const val DEFAULT_LINE_COLOR = Color.RED
        private const val DEFAULT_LINE_WIDTH = 50f
    }
}
