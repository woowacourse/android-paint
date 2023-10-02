package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.view.model.RichPaths

class PaintView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var currentPaint: Paint = defaultLinePaint()
    private var currentPath: Path = Path()

    private var richPaths: RichPaths = RichPaths()
    private var onAddLine: (Path, Paint) -> Unit = { path, paint ->
        richPaths = RichPaths(richPaths.data + (path to paint))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPath(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startPaint(event.x, event.y)
                movePaint(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                movePaint(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                cacheCurrentPaint()
                return true
            }

            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setColor(color: Int) {
        currentPaint.color = color
    }

    fun setWidth(strokeWidth: Float) {
        currentPaint.strokeWidth = strokeWidth
    }

    fun setRichPaths(richPaths: RichPaths) {
        this.richPaths = richPaths
    }

    fun setOnAddLine(onAddLine: (Path, Paint) -> Unit) {
        this.onAddLine = onAddLine
    }

    private fun startPaint(pointX: Float, pointY: Float) {
        currentPath.moveTo(pointX, pointY)
    }

    private fun movePaint(pointX: Float, pointY: Float) {
        currentPath.lineTo(pointX, pointY)
    }

    private fun cacheCurrentPaint() {
        onAddLine(currentPath, currentPaint)
        currentPath = Path()
        currentPaint = Paint(currentPaint)
    }

    private fun drawPath(canvas: Canvas) {
        richPaths.data.forEach {
            canvas.drawPath(it.first, it.second)
        }
        canvas.drawPath(currentPath, currentPaint)
    }

    companion object {
        fun defaultLinePaint(): Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            pathEffect = CornerPathEffect(10F)
        }
    }
}
