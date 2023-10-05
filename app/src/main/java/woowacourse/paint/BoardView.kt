package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.ColorPalette

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint()
    private lateinit var brushType: BrushType
    private var startX = DEFAULT_COORDINATE
    private var startY = DEFAULT_COORDINATE
    private lateinit var storedCanvas: Canvas
    private lateinit var bitmap: Bitmap

    fun changeColor(colorPalette: ColorPalette) {
        paint.color = ContextCompat.getColor(context, colorPalette.color)
    }

    fun setWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun setBrush(brushType: BrushType) {
        this.brushType = brushType
    }

    fun erase() {
        bitmap.eraseColor(Color.TRANSPARENT)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        storedCanvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setDefaultPaint()
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        canvas.drawPath(path, paint)
    }

    private fun setDefaultPaint() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startDrawing(event, brushType)
            MotionEvent.ACTION_MOVE -> moveDrawing(event, brushType)
            MotionEvent.ACTION_UP -> endDrawing()
        }
        invalidate()
        return true
    }

    private fun startDrawing(event: MotionEvent, brushType: BrushType) {
        when (brushType) {
            BrushType.PEN -> {
                path.moveTo(event.x, event.y)
                path.lineTo(event.x, event.y)
            }
            else -> {
                startX = event.x
                startY = event.y
            }
        }
    }

    private fun moveDrawing(event: MotionEvent, brushType: BrushType) {
        when (brushType) {
            BrushType.PEN -> {
                path.lineTo(event.x, event.y)
            }
            BrushType.RECTANGLE -> {
                path.reset()
                path.addRect(startX, startY, event.x, event.y, Path.Direction.CCW)
                invalidate()
            }
            BrushType.CIRCLE -> {
                path.reset()
                path.addCircle(startX, startY, event.x - startX, Path.Direction.CCW)
                invalidate()
            }
            BrushType.ERASER -> {}
        }
    }

    private fun endDrawing() {
        storedCanvas.drawPath(path, paint)
        path.reset()
    }

    companion object {
        private const val DEFAULT_COORDINATE = 0.0f
    }
}
