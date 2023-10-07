package woowacourse.paint.model.brush

import android.graphics.Paint

object Circle : Brush() {
    override fun setStyle() {
        paint.style = Paint.Style.FILL
    }
}
