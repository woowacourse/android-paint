package woowacourse.paint.model

import android.graphics.Canvas
import woowacourse.paint.model.drawable.DrawableElement

data class DrawableHistory(
    private val _elements: MutableList<DrawableElement> = mutableListOf(),
) {
    val elements: List<DrawableElement>
        get() = _elements.toList()

    fun add(element: DrawableElement) {
        _elements.add(element)
    }

    fun drawAll(canvas: Canvas) {
        elements.forEach {
            it.drawCurrent(canvas)
        }
    }
}
