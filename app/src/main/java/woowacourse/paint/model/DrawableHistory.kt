package woowacourse.paint.model

import android.graphics.Canvas
import woowacourse.paint.model.drawable.DrawableElement

class DrawableHistory {
    private val elements: MutableList<DrawableElement> = mutableListOf()
    private val undoHistory: MutableList<DrawableElement> = mutableListOf()

    fun add(element: DrawableElement) {
        elements.add(element)
        undoHistory.clear()
    }

    fun drawAll(canvas: Canvas) {
        elements.forEach {
            it.drawCurrent(canvas)
        }
    }

    fun undo() {
        val element = elements.removeLastOrNull() ?: return
        undoHistory.add(element)
    }

    fun redo() {
        val element = undoHistory.removeLastOrNull() ?: return
        elements.add(element)
    }

    fun clear() {
        elements.clear()
        undoHistory.clear()
    }
}
