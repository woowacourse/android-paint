package woowacourse.paint.model

import android.graphics.Canvas
import woowacourse.paint.model.drawable.DrawableElement

class DrawableHistory {
    private val undoes: MutableList<DrawableElement> = mutableListOf()
    private val redoes: MutableList<DrawableElement> = mutableListOf()

    fun add(element: DrawableElement) {
        undoes.add(element)
        redoes.clear()
    }

    fun drawAll(canvas: Canvas) {
        undoes.forEach {
            it.drawCurrent(canvas)
        }
    }

    fun undo() {
        val element = undoes.removeLastOrNull() ?: return
        redoes.add(element)
    }

    fun redo() {
        val element = redoes.removeLastOrNull() ?: return
        undoes.add(element)
    }

    fun clear() {
        undoes.clear()
        redoes.clear()
    }
}
