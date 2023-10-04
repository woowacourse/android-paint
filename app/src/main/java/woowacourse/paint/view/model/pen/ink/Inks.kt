package woowacourse.paint.view.model.pen.ink

import android.graphics.Canvas

data class Inks(val data: List<Ink> = emptyList()) {
    fun draw(canvas: Canvas) {
        data.forEach {
            it.draw(canvas)
        }
    }
}
