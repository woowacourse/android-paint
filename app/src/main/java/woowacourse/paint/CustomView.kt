package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomView(context: Context, attrs: AttributeSet) :
    View(context, attrs) {


    private val strokes = mutableListOf<Stroke>()
    private var currentPath: Path? = null
    private var currentPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    init {
        isFocusable = true
        isFocusableInTouchMode = true

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0, 0
        ).apply {
            try {
                currentPaint.color = getColor(R.styleable.CustomView_lineColor, Color.RED)
                currentPaint.strokeWidth = getDimension(R.styleable.CustomView_lineWidth, 50f)
                println(currentPaint.color)
                println(currentPaint.strokeWidth)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (stroke in strokes) {
            canvas.drawPath(stroke.path, stroke.paint)
        }
        currentPath?.let { canvas.drawPath(it, currentPaint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path().apply { moveTo(pointX, pointY) }
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath?.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                currentPath?.let {
                    strokes.add(Stroke(it, Paint(currentPaint)))
                }
                currentPath = null
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
}
