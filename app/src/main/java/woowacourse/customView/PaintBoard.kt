package woowacourse.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.model.BoardColor

class PaintBoard(
    context: Context,
    attr: AttributeSet,
) : View(context, attr) {
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.RED
        strokeWidth = 50f
    }

    private val drawingPaths: MutableList<PathWithColor> = mutableListOf()
    private val newPath: Path = Path()
    var nowColor: BoardColor = BoardColor.RedColor
    var nowStrokeWidth: Float = 50f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawingPaths.forEach { pathWithColor ->
            canvas?.drawPath(
                pathWithColor.path,
                paint.apply {
                    color = ContextCompat.getColor(context, pathWithColor.color.colorInt)
                    strokeWidth = pathWithColor.strokeWidth
                },
            )
        }
        canvas?.drawPath(
            newPath,
            paint.apply {
                color = ContextCompat.getColor(context, nowColor.colorInt)
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
                drawingPaths.add(PathWithColor(Path(newPath), nowColor, nowStrokeWidth))
                newPath.reset()
            }
        }
        invalidate()
        return true
    }
}
