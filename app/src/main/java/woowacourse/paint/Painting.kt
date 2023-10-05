package woowacourse.paint

import android.graphics.Canvas

class Painting(private val records: MutableList<Line> = mutableListOf()) {

    fun drawOn(canvas: Canvas) {
        records.forEach {
            canvas.drawPath(it.path, it.brush)
        }
    }

    fun drawLine(
        line: Line,
        onEmptyLine: (() -> Line)
    ) {
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
