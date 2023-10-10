package woowacourse.paint.view.model.pen.ink

import android.graphics.Canvas

data class Inks(val value: List<Ink> = emptyList()) {
    fun draw(canvas: Canvas) {
        value.forEach {
            it.draw(canvas)
        }
    }
}
