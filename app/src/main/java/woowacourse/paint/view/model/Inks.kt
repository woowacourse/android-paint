package woowacourse.paint.view.model

import android.graphics.Canvas
import woowacourse.paint.view.model.pen.Ink

data class Inks(val data: List<Ink> = emptyList()) {
    fun draw(canvas: Canvas) {
        data.forEach {
            it.draw(canvas)
        }
    }
}
