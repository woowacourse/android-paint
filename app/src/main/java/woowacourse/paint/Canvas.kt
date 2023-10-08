package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import woowacourse.paint.model.Brush
import woowacourse.paint.model.Circle
import woowacourse.paint.model.PathPaint
import woowacourse.paint.model.Rectangle
import woowacourse.paint.util.drawCircle

class Canvas(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {
    private val pathPaints: MutableList<PathPaint> = mutableListOf()
    private var currentPathPaint: PathPaint = PathPaint()

    private val undo: MutableList<Any> = mutableListOf()
    private val redo: MutableList<Any> = mutableListOf()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setLayerType(LAYER_TYPE_SOFTWARE, null)

        pathPaints.forEach {
            canvas.draw(it)
        }
    }

    private fun Canvas.draw(pathPaint: PathPaint) {
        when (pathPaint.brush) {
            Brush.PEN -> {
                drawPath(pathPaint.path, pathPaint.paint)
            }

            Brush.RECT -> {
                drawRect(pathPaint.shape as Rectangle, pathPaint.paint)
            }

            Brush.CIRCLE -> {
                drawCircle(pathPaint.shape as Circle, pathPaint.paint)
            }

            Brush.ERASER -> {
                drawPath(pathPaint.path, pathPaint.paint)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pathPaints.add(currentPathPaint)
                currentPathPaint.moveToPath(event.x, event.y)
                currentPathPaint.drawToPath(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPathPaint.drawToPath(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                undo.add(currentPathPaint)
                currentPathPaint = currentPathPaint.resetPaint()
            }

            else -> {
                return super.onTouchEvent(event)
            }
        }
        invalidate()
        return true
    }

    fun setStrokeSize(size: Float) {
        currentPathPaint.setPaintStrokeSize(size)
    }

    fun setPaintColor(@ColorInt color: Int) {
        currentPathPaint.setPaintColor(color)
    }

    fun setChangeBrush(brush: Brush) {
        currentPathPaint.brush = brush
    }

    fun clear() {
        undo.add(pathPaints.toList())
        pathPaints.clear()
        invalidate()
    }

    fun undo() {
        runCatching {
            processUndo(undo.last())
            redo.add(undo.last())
            undo.removeLast()
        }
    }

    private fun processUndo(action: Any) {
        when (action) {
            is List<*> -> {
                pathPaints.addAll(action.map { it as PathPaint })
            }

            is PathPaint -> {
                pathPaints.removeIf { it.path == action.path }
            }
        }
        invalidate()
    }

    fun redo() {
        runCatching {
            processRedo(redo.last())
            undo.add(redo.last())
            redo.removeLast()
        }
    }

    private fun processRedo(action: Any) {
        when (action) {
            is List<*> -> {
                pathPaints.clear()
            }

            is PathPaint -> {
                pathPaints.add(action)
            }
        }
        invalidate()
    }
}
