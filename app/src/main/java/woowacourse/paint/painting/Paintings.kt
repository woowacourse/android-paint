package woowacourse.paint.painting

import android.graphics.Canvas
import woowacourse.paint.painting.figure.Circle
import woowacourse.paint.painting.figure.Figure
import woowacourse.paint.painting.figure.Line
import woowacourse.paint.painting.figure.Rectangle

class Paintings(private val records: MutableList<Figure> = mutableListOf()) {

    fun draw(canvas: Canvas) {
        records.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    fun drawFigure(figure: Figure, onEmptyLine: () -> Figure) {
        when (figure) {
            is Line -> drawLine(figure) {
                onEmptyLine()
            }

            is Rectangle, is Circle -> records.add(figure)
        }
    }

    private fun drawLine(line: Line, onEmptyLine: () -> Figure) {
        if (line.length == EMPTY_LINE) {
            records.add(onEmptyLine())
        } else {
            records.add(line)
        }
    }

    companion object {

        private const val EMPTY_LINE = 0f
    }
}
