package woowacourse.paint.graphics

import android.graphics.RectF

class AutoSortedRect(left: Float, top: Float, right: Float, bottom: Float) {
    val left: Float = minOf(left, right)
    val top: Float = minOf(top, bottom)
    val right: Float = maxOf(left, right)
    val bottom: Float = maxOf(top, bottom)

    fun toRectF() = RectF(left, top, right, bottom)
}
