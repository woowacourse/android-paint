package woowacourse.paint.custom.view.model

import android.graphics.Canvas

class Painted {
    private val drawable = mutableListOf<Drawable>()

    fun add(drawable: Drawable) {
        this.drawable.add(drawable)
    }

    fun draw(canvas: Canvas) {
        drawable.forEach { it.draw(canvas) }
    }
}
