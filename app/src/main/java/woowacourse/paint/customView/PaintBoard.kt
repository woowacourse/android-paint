package woowacourse.paint.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt

class PaintBoard @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
) : View(context, attr) {
    private val drawingPaths: MutableList<DrawingPathInfo> = mutableListOf()
    private val newPath: Path = Path()

    @ColorInt
    var nowColor: Int = 0xFF0000
        set(value) {
            field = value
            updateNowPaint()
        }
    var nowStrokeWidth: Float = 50f
        set(value) {
            field = value
            updateNowPaint()
        }

    private var nowPaint: Paint = getPaint(nowColor, nowStrokeWidth)

    private fun updateNowPaint() {
        nowPaint = getPaint(nowColor, nowStrokeWidth)
    }

    private fun getPaint(@ColorInt colorInt: Int = Color.RED, strokeWidth: Float = 50f): Paint =
        Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            color = colorInt
            this.strokeWidth = strokeWidth
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawingPaths.forEach { pathInfo ->
            canvas?.drawPath(pathInfo.path, pathInfo.paint)
        }
        canvas?.drawPath(newPath, nowPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                newPath.reset()
                newPath.moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                newPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                drawingPaths.add(DrawingPathInfo(Path(newPath), getPaint(nowColor, nowStrokeWidth)))
                newPath.reset()
            }
        }
        invalidate()
        return true
    }
}
