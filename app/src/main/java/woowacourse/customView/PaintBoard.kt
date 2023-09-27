package woowacourse.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import woowacourse.model.BoardColor

class PaintBoard(
    context: Context,
    attr: AttributeSet,
) : View(context, attr) {
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        color = Color.RED
        strokeWidth = 50f
    }

    private val drawingPaths: MutableList<DrawingPathInfo> = mutableListOf()
    private val newPath: Path = Path()

    @ColorInt
    var nowColor: Int = ContextCompat.getColor(context, BoardColor.RedColor.colorInt)
    var nowStrokeWidth: Float = 50f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawingPaths.forEach { pathInfo ->
            canvas?.drawPath(
                pathInfo.path,
                paint.apply {
                    color = pathInfo.boardColor
                    strokeWidth = pathInfo.strokeWidth
                },
            )
        }
        canvas?.drawPath(
            newPath,
            paint.apply {
                color = nowColor
                strokeWidth = nowStrokeWidth
            },
        )
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
                drawingPaths.add(DrawingPathInfo(Path(newPath), nowColor, nowStrokeWidth))
                newPath.reset()
            }
        }
        invalidate()
        return true
    }
}
