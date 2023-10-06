package woowacourse.paint.model

import android.graphics.Canvas
import woowacourse.paint.model.drawable.DrawableElement
import woowacourse.paint.model.drawable.DrawablePath
import woowacourse.paint.model.drawable.DrawableSquare

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
            drawElement(canvas, it)
        }
    }

    private fun drawElement(
        canvas: Canvas,
        it: DrawableElement,
    ) {
        when (it) {
            is DrawablePath -> canvas.drawPath(it.path, it.paint)
            is DrawableSquare -> canvas.drawRect(it.rect, it.paint)
        }
    }
}
