package woowacourse.paint.view

import android.graphics.Canvas

class PathPainterHistory(
    private val pathPainters: MutableList<PathPainter> = mutableListOf(),
) {
    fun add(pathPainter: PathPainter) {
        pathPainters.add(pathPainter)
    }

    fun drawPath(canvas: Canvas) {
        pathPainters.forEach { painter ->
            painter.drawPath(canvas)
        }
    }
}
