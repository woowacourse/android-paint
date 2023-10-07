package woowacourse.paint.painting

import android.graphics.Canvas
import woowacourse.paint.painting.figure.Circle
import woowacourse.paint.painting.figure.Eraser
import woowacourse.paint.painting.figure.Figure
import woowacourse.paint.painting.figure.Line
import woowacourse.paint.painting.figure.Rectangle

class Paintings(private val values: MutableList<Figure> = mutableListOf()) {

    private val cache: MutableList<List<Figure>> = mutableListOf()

    fun draw(canvas: Canvas) {
        values.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    fun drawFigure(figure: Figure, onEmptyLine: () -> Figure) {
        when (figure) {
            is Line -> drawLine(figure) {
                onEmptyLine()
            }

            is Rectangle, is Circle -> values.add(figure)

            is Eraser -> erase(figure)
        }
    }

    private fun drawLine(line: Line, onEmptyLine: () -> Figure) {
        if (line.length == EMPTY_LINE) {
            values.add(onEmptyLine())
        } else {
            values.add(line)
        }
    }

    private fun erase(eraser: Eraser) {
        val figures = values.filter {
            eraser.isOverlapped(it.path)
        }

        values.removeAll(figures)
        cache.add(figures)
    }

    fun undo(onFailure: () -> Unit) {
        runCatching {
            cache.add(listOf(values.removeLast()))
        }.onFailure {
            onFailure()
        }
    }

    fun redo(onFailure: () -> Unit) {
        runCatching {
            values.addAll(cache.removeLast())
        }.onFailure {
            onFailure()
        }
    }

    companion object {

        private const val EMPTY_LINE = 0f
    }
}
