package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.domain.BrushType.CIRCLE
import com.example.domain.BrushType.ERASER
import com.example.domain.BrushType.LINE
import com.example.domain.BrushType.RECTANGLE
import woowacourse.paint.Painting
import woowacourse.paint.Paintings

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var path = Path()
    private var paint = Paint()

    private val paintings = Paintings()

    private var brushType = LINE

    private var startPointX = START_DEFAULT_X_COORDINATE
    private var startPointY = START_DEFAULT_Y_COORDINATE

    init {
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintings.painting.forEach { painting ->
            canvas.drawPath(painting.path, painting.paint)
        }
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startPointX = pointX
                startPointY = pointY
                doActionDown(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE -> {
                doActionMove(pointX, pointY)
            }
            MotionEvent.ACTION_UP -> {
                doActionUp(pointX, pointY)
            }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun setupPaint(width: Float = 0F, color: Int = Color.BLACK) {
        path = Path()
        paint = Paint()

        paint.isAntiAlias = true
        paint.color = color
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = width
    }

    fun setupPen() {
        setupPaint(paint.strokeWidth, paint.color)
        brushType = LINE
    }

    fun setupCircle() {
        setupPaint(paint.strokeWidth, paint.color)
        paint.style = Paint.Style.FILL
        brushType = CIRCLE
    }

    fun setupRectangle() {
        setupPaint(paint.strokeWidth, paint.color)
        paint.style = Paint.Style.FILL
        brushType = RECTANGLE
    }

    fun setMyStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun setMyStrokeColor(color: Int) {
        paint.color = color
    }

    private fun doActionDown(pointX: Float, pointY: Float) {
        when (brushType) {
            LINE -> path.moveTo(pointX, pointY)
            ERASER -> { }
            else -> {
                startPointX = pointX
                startPointY = pointY
            }
        }
    }

    private fun doActionMove(pointX: Float, pointY: Float) {
        when (brushType) {
            CIRCLE -> {
                path.reset()
                path.addArc(startPointX, startPointY, pointX, pointY, 0f, 360f)
            }
            RECTANGLE -> {
                path.reset()
                path.addRect(startPointX, startPointY, pointX, pointY, Path.Direction.CCW)
            }
            LINE -> path.lineTo(pointX, pointY)
            ERASER -> { }
        }
    }

    private fun doActionUp(pointX: Float, pointY: Float) {
        when (brushType) {
            CIRCLE -> {
                paintings.storePainting(Painting(paint, path))
                setupCircle()
            }
            RECTANGLE -> {
                paintings.storePainting(Painting(paint, path))
                setupRectangle()
            }
            LINE -> {
                path.lineTo(pointX, pointY)
                paintings.storePainting(Painting(paint, path))
                setupPaint(paint.strokeWidth, paint.color)
            }
            ERASER -> { }
        }
    }

    companion object {
        private const val START_DEFAULT_X_COORDINATE = 0f
        private const val START_DEFAULT_Y_COORDINATE = 0f
    }
}
