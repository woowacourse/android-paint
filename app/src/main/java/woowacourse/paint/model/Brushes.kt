package woowacourse.paint.model

import android.graphics.Canvas

class Brushes(private var history: List<Brush> = listOf()) {

    operator fun plus(brush: Brush): Brushes {
        return Brushes(history.plus(brush))
    }

    fun drawOn(canvas: Canvas) {
        history.forEach { brush -> brush.drawOn(canvas) }
    }

    fun isEmpty(): Boolean {
        return history.isEmpty()
    }
}
