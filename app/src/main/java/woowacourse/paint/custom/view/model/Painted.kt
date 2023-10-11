package woowacourse.paint.custom.view.model

import android.graphics.Canvas
import java.util.Stack

class Painted {
    private val drawable = Stack<Drawable>()
    private val history = Stack<Drawable>()

    fun add(drawable: Drawable) {
        this.drawable.add(drawable)
    }

    fun draw(canvas: Canvas) {
        drawable.forEach { it.draw(canvas) }
    }

    fun undo() {
        if (drawable.empty().not()) {
            history.add(drawable.pop())
        }
    }

    fun redo() {
        if (history.empty().not()) {
            drawable.add(history.pop())
        }
    }
}
