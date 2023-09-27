package woowacourse.paint.customVeiw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.lang.Math.sqrt

class PaintingPaper constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var path = Path()
    private val records = mutableListOf<Record>()
    private val paint = Paint()
    private var prevPoint = Pair(0f, 0f)

    class Record(val path: Path, val paint: Paint)

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        records.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean = when (event.action) {
        MotionEvent.ACTION_DOWN -> {
            Log.d(TAG, "ACTION_DOWN")
            records += getNewRecord().apply {
                path.moveTo(event.x, event.y)
            }
            true
        }

        MotionEvent.ACTION_MOVE -> {
            if (calculateDistance(event, prevPoint) >= 1) {
                prevPoint = Pair(event.x, event.y)
                records.last().path.lineTo(event.x, event.y)
                invalidate()
            }
            true
        }

        MotionEvent.ACTION_UP -> {
            Log.d(TAG, "ACTION_UP")
            true
        }

        else -> super.onTouchEvent(event)
    }

    private fun getNewRecord(): Record {
        return Record(getNewPath(), getNewPaint())
    }

    private fun getNewPath(): Path {
        return Path()
    }

    private fun getNewPaint(): Paint {
        return Paint().apply {
            strokeWidth = 10F
            style = Paint.Style.STROKE
        }
    }

    private fun calculateDistance(event: MotionEvent, point: Pair<Float, Float>): Float {
        val dx = event.x - point.first
        val dy = event.y - point.second
        return sqrt((dx * dx + dy * dy).toDouble()).toFloat()
    }

    private fun setupPaint() {
        paint.color = Color.RED
    }

    companion object {
        private val TAG = PaintingPaper::class.java.simpleName
    }
}
