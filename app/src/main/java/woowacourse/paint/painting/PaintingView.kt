package woowacourse.paint.painting

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import woowacourse.paint.R
import woowacourse.paint.painting.figure.Circle
import woowacourse.paint.painting.figure.Eraser
import woowacourse.paint.painting.figure.Figure
import woowacourse.paint.painting.figure.FigureType
import woowacourse.paint.painting.figure.Line
import woowacourse.paint.painting.figure.Rectangle

class PaintingView(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {

    private val paintings = Paintings()

    private var figure: Figure = Line(
        path = Path(),
        paint = Paint().apply {
            style = Paint.Style.STROKE
            color = context.getColor(R.color.red)
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    )

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paintings.draw(canvas)
        canvas.drawPath(figure.path, figure.paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        with(event) {
            when (action) {
                MotionEvent.ACTION_DOWN -> figure.begin(x, y)

                MotionEvent.ACTION_MOVE -> figure.extend(x, y)

                MotionEvent.ACTION_UP -> {
                    paintings.drawFigure(figure) {
                        Line.dot(x, y, figure.paint)
                    }

                    figure = figure.copy(Path())
                }
            }
        }
        invalidate()
        return true
    }

    fun undo() {
        paintings.undo {
            Toast.makeText(
                context,
                context.getString(R.string.painting_no_such_element_to_undo),
                Toast.LENGTH_SHORT
            ).show()
        }
        invalidate()
    }

    fun redo() {
        paintings.redo {
            Toast.makeText(
                context,
                context.getString(R.string.painting_no_such_element_to_redo),
                Toast.LENGTH_SHORT
            ).show()
        }
        invalidate()
    }

    fun setFigureType(type: FigureType) {
        figure = when (type) {
            FigureType.LINE -> Line(Path(), Paint(figure.paint))
            FigureType.RECTANGLE -> Rectangle(Path(), Paint(figure.paint))
            FigureType.CIRCLE -> Circle(Path(), Paint(figure.paint))
            FigureType.ERASER -> Eraser(Path(), Paint(figure.paint))
        }
    }

    fun setBrushColor(color: Int) {
        figure = figure.copy(
            paint = Paint(figure.paint).apply {
                this.color = color
            }
        )
    }

    fun setBrushWidth(width: Float) {
        figure = figure.copy(
            paint = Paint(figure.paint).apply {
                this.strokeWidth = width
            }
        )
    }
}
