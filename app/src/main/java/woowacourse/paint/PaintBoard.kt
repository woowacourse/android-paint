package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
import android.view.View
import androidx.annotation.ColorRes

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val history: History = History()
    private val paint: Paint = Paint()
    private var path: Path = Path()

    init {
        setupPaintSetting()
    }

    private fun setupPaintSetting() {
        paint.apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = DEFAULT_SIZE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            color = context.getColor(DEFAULT_COLOR)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            drawHistory()
            drawPath(path, paint)
        }
    }

    private fun Canvas.drawHistory() {
        history.paintings.forEach { painting ->
            drawPath(painting.path, painting.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            ACTION_DOWN -> initPath(event.x, event.y)
            ACTION_MOVE -> drawPath(event.x, event.y)
            ACTION_UP -> savePath()
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun initPath(pointX: Float, pointY: Float) {
        path = Path()
        path.moveTo(pointX, pointY)
        path.lineTo(pointX, pointY)
    }

    private fun drawPath(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    private fun savePath() {
        history.add(Painting(path, Paint(paint)))
    }

    fun changeSize(value: Float) {
        paint.strokeWidth = value
    }

    fun changeColor(value: Int) {
        paint.color = context.getColor(value)
    }

    companion object {
        @ColorRes private val DEFAULT_COLOR = R.color.blue
        const val DEFAULT_SIZE = 20F
        val COLORS =
            listOf(R.color.red, R.color.orange, R.color.yellow, R.color.green, R.color.blue)
    }
}
