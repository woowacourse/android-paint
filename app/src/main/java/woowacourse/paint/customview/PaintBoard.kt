package woowacourse.paint.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style.FILL
import android.graphics.Paint.Style.STROKE
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import woowacourse.paint.R
import woowacourse.paint.model.History
import woowacourse.paint.model.Painting
import woowacourse.paint.model.Tools
import woowacourse.paint.model.Tools.CIRCLE
import woowacourse.paint.model.Tools.ERASER
import woowacourse.paint.model.Tools.LINE
import woowacourse.paint.model.Tools.RECTANGLE
import woowacourse.paint.shape.Circle
import woowacourse.paint.shape.Line
import woowacourse.paint.shape.Rectangle

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val history: History = History()
    private lateinit var painting: Painting

    init {
        setupPaintSetting()
    }

    private fun setupPaintSetting() {
        setLayerType(LAYER_TYPE_HARDWARE, null)
        painting = Painting(
            shape = Line(),
            paint = Paint().apply {
                isAntiAlias = true
                style = STROKE
                strokeWidth = DEFAULT_SIZE
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
                color = context.getColor(DEFAULT_COLOR)
            },
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            drawHistory()
            painting.drawPath(this)
        }
    }

    private fun Canvas.drawHistory() {
        history.paintings.forEach { painting ->
            painting.drawPath(this)
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
        painting.startDraw(pointX, pointY)
    }

    private fun drawPath(pointX: Float, pointY: Float) {
        painting.onDraw(pointX, pointY)
    }

    private fun savePath() {
        history.add(painting)
        painting = painting.copy()
    }

    fun changeSize(value: Float) {
        painting.changeSize(value)
    }

    fun changeTool(value: Tools) {
        painting = when (value) {
            LINE -> painting.changeShape(Line(), STROKE)
            CIRCLE -> painting.changeShape(Circle(), FILL)
            RECTANGLE -> painting.changeShape(Rectangle(), FILL)
            ERASER -> painting.changeShape(Line(), STROKE, true)
        }
    }

    fun changeColor(@ColorRes value: Int) {
        painting.changeColor(context.getColor(value))
    }

    fun clear() {
        history.clear()
        invalidate()
    }

    companion object {
        @ColorRes
        private val DEFAULT_COLOR = R.color.blue
        const val DEFAULT_SIZE = 20F

        @ArrayRes
        val COLORS =
            intArrayOf(R.color.red, R.color.orange, R.color.yellow, R.color.green, R.color.blue)
    }
}
