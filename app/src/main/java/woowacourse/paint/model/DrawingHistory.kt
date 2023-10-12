package woowacourse.paint.model

import android.graphics.Canvas

class DrawingHistory {

    private val _history = mutableListOf<Drawing>()

    fun add(drawing: Drawing) = _history.add(drawing)

    fun drawAll(canvas: Canvas) {
        _history.forEach { canvas.drawPath(it.path, it.paint) }
    }

    fun clearAll() = _history.clear()
}
