package woowacourse.paint.custom.view.model

import android.graphics.Canvas

class CurveLines {
    private val lines = mutableListOf<Line>()

    fun add(line: Line) {
        lines.add(line)
    }

    fun draw(canvas: Canvas) {
        lines.forEach { canvas.drawPath(it.path, it.paint) }
    }
}
