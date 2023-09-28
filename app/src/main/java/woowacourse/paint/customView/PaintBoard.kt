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
import androidx.databinding.BindingAdapter

class PaintBoard @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
) : View(context, attr) {
    private val drawnPaths: MutableList<DrawingPathInfo> = mutableListOf()
    private val drawingPath: Path = Path()

    @ColorInt
    var currentColor: Int = 0xFF0000
        set(value) {
            field = value
            updateNowPaint()
        }
    var currentStrokeWidth: Float = 50f
        set(value) {
            field = value
            updateNowPaint()
        }

    private var currentPaint: Paint = getPaint(currentColor, currentStrokeWidth)

    private fun updateNowPaint() {
        currentPaint = getPaint(currentColor, currentStrokeWidth)
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

        drawnPaths.forEach { pathInfo ->
            canvas?.drawPath(pathInfo.path, pathInfo.paint)
        }
        canvas?.drawPath(drawingPath, currentPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawingPath.reset()
                drawingPath.moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                drawingPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                drawnPaths.add(
                    DrawingPathInfo(
                        Path(drawingPath),
                        getPaint(currentColor, currentStrokeWidth),
                    ),
                )
                drawingPath.reset()
            }
        }
        invalidate()
        return true
    }

    companion object {
        @JvmStatic
        @BindingAdapter("app:paint_board_currentColor")
        fun PaintBoard.setBindingCurrentColor(@ColorInt colorInt: Int) {
            this.currentColor = colorInt
        }

        @JvmStatic
        @BindingAdapter("app:paint_board_currentStrokeWidth")
        fun PaintBoard.setBindingCurrentStrokeWidth(currentStrokeWidth: Float) {
            this.currentStrokeWidth = currentStrokeWidth
        }
    }
}
