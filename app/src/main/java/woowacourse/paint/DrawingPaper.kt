package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingPaper(
    private val context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {

    private val pathRecords: MutableList<Path> = mutableListOf(Path())
    private val paintRecords: MutableList<Paint> = mutableListOf(Paint())
    private var brushWidth = 0f
    private var brushColor = R.color.black

    fun changeWidth(width: Float) {
        val newPath = Path()
        val newPaint = Paint()

        pathRecords.add(newPath)
        paintRecords.add(newPaint)
        brushWidth = width
    }

    fun changeColor(color: Int) {
        val newPath = Path()
        val newPaint = Paint()

        pathRecords.add(newPath)
        paintRecords.add(newPaint)
        brushColor = color
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (record in paintRecords.indices) {
            val path = pathRecords[record]
            val paint = paintRecords[record].setBrush()

            paintRecords.last().strokeWidth = brushWidth
            paintRecords.last().color = context.getColor(brushColor)

            canvas.drawPath(path, paint)
        }
    }

    private fun Paint.setBrush(): Paint = apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        strokeMiter = SOFT_ANGLE
        isAntiAlias = true
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pathRecords.last().moveTo(event.x, event.y)
                pathRecords.last().lineTo(event.x, event.y)

                pathRecords.last().moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> pathRecords.last().lineTo(event.x, event.y)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    companion object {
        private const val SOFT_ANGLE = 0f
    }

}