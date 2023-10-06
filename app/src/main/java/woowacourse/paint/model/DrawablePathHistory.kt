package woowacourse.paint.model

import android.graphics.Canvas

data class DrawablePathHistory(
    private val _paths: MutableList<DrawablePath> = mutableListOf(),
) {
    val paths: List<DrawablePath>
        get() = _paths.toList()

    fun add(path: DrawablePath) {
        _paths.add(path)
    }

    fun drawAll(canvas: Canvas) {
        paths.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }
}
