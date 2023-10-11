package woowacourse.paint.custom.view.model

import android.graphics.Canvas

class Painted {
    private val drawable = mutableListOf<Drawable>()

    fun add(line: Line) {
        drawable.add(line)
    }

    fun draw(canvas: Canvas) {
        drawable.forEach {
            when (it) {
                is Line -> it.draw(canvas)
            }
        }
    }
}
