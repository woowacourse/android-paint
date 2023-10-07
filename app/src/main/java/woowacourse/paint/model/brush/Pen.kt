package woowacourse.paint.model.brush

import android.graphics.Paint

object Pen : Brush() {
    override fun setStyle() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }
}
