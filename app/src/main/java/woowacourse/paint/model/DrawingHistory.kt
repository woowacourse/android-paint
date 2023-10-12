package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Path

class DrawingHistory {

    private val _history = mutableListOf<Drawing>()
    val history: List<Drawing>
        get() = _history.map {
            it.copy(
                path = Path(it.path),
                paint = Paint(it.paint),
            )
        }.toList()

    fun add(drawing: Drawing) = _history.add(drawing)

    fun clearAll() = _history.clear()
}
