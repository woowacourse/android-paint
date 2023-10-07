package woowacourse.paint.model.brush

import android.graphics.Paint

object Rectangle : Brush() {
    override fun setStyle() {
        paint.style = Paint.Style.FILL
    }
}
