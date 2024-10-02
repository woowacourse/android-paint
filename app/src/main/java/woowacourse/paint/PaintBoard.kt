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

class PaintBoard(context: Context, attr: AttributeSet) : View(context, attr) {
    private val lines: MutableList<Line> = mutableListOf()

    private var path: Path = Path()
    private var paint: Paint = Paint()

    init {
        initPaint()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lines.add(Line(path, paint))
                path.moveTo(x, y)
            }

            MotionEvent.ACTION_MOVE -> path.lineTo(x, y)

            MotionEvent.ACTION_UP -> {
                createNewPath()
                createNewPaint()
            }

            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { line ->
            canvas.drawPath(line.path, line.paint)
        }
    }

    private fun initPaint() {
        paint =
            paint.apply {
                style = Paint.Style.STROKE
                strokeJoin = Paint.Join.ROUND
                strokeCap = Paint.Cap.ROUND
                color = DEFAULT_PAINT_COLOR_RES
                strokeWidth = DEFAULT_STROKE_WIDTH
            }
    }

    private fun createNewPath() {
        path = Path()
    }

    private fun createNewPaint() {
        paint =
            Paint().apply {
                style = Paint.Style.STROKE
                strokeJoin = Paint.Join.ROUND
                strokeCap = Paint.Cap.ROUND
                color = paint.color
                strokeWidth = paint.strokeWidth
            }
    }

    fun setPaintColor(color: Int) {
        paint.color = color
    }

    fun setPaintStrokeWidth(strokeWidth: Float) {
        paint.strokeWidth = strokeWidth
    }

    companion object {
        val DEFAULT_PAINT_COLOR_RES: Int = R.color.red
        const val DEFAULT_STROKE_WIDTH: Float = 30.0f
    }
}
