package woowacourse.paint.painting

import android.graphics.Canvas
import woowacourse.paint.painting.cache.Action
import woowacourse.paint.painting.cache.PaintingCache
import woowacourse.paint.painting.cache.PaintingHistory
import woowacourse.paint.painting.figure.Circle
import woowacourse.paint.painting.figure.Eraser
import woowacourse.paint.painting.figure.Figure
import woowacourse.paint.painting.figure.Line
import woowacourse.paint.painting.figure.Rectangle

class Paintings {

    private val cache = PaintingCache()
    private val values: MutableList<Figure> = mutableListOf()

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

            is Rectangle, is Circle -> {
                values.add(figure)
                cache.add(PaintingHistory.added(figure))
            }

            is Eraser -> erase(figure)
        }
    }

    private fun drawLine(line: Line, onEmptyLine: () -> Figure) {
        if (line.length == EMPTY_LINE) {
            cache.add(PaintingHistory.added(onEmptyLine()))
            values.add(onEmptyLine())
        } else {
            cache.add(PaintingHistory.added(line))
            values.add(line)
        }
    }

    private fun erase(eraser: Eraser) {
        val figures = values.filter {
            eraser.isOverlapped(it.path)
        }

        values.removeAll { figures.contains(it) }
        cache.add(PaintingHistory.removed(*figures.toTypedArray()))
    }

    fun undo(onFailure: () -> Unit) {
        cache.undo(
            onSuccess = ::drawHistory,
            onFailure = onFailure
        )
    }

    fun redo(onFailure: () -> Unit) {
        cache.redo(
            onSuccess = ::drawHistory,
            onFailure = onFailure
        )
    }

    private fun drawHistory(history: PaintingHistory) {
        if (history.action == Action.REMOVE) {
            values.removeAll { history.figures.contains(it) }
        } else {
            values.addAll(history.figures)
        }
    }

    companion object {

        private const val EMPTY_LINE = 0f
    }
}
