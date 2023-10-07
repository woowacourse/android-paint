package woowacourse.paint.view

import android.graphics.Canvas

class PainterHistory(
    private val painters: MutableList<Painter> = mutableListOf(),
) {
    fun add(painter: Painter) {
        painters.add(painter)
    }

    fun draw(canvas: Canvas) {
        painters.forEach { painter ->
            painter.draw(canvas)
        }
    }
}
