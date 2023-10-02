package woowacourse.paint.paintBoard

import android.graphics.Paint

data class Brush(
    val paint: Paint = Paint()
) {

    init {
        with(paint) {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            strokeMiter = SOFT_ANGLE
            isAntiAlias = true
        }
    }

    fun changeBrush(width: Float): Brush = Brush(
        Paint().apply {
            strokeWidth = width
            color = paint.color
        }
    )


    fun changeBrush(selectedColor: Int): Brush = Brush(
        Paint().apply {
            strokeWidth = paint.strokeWidth
            color = selectedColor
        }
    )


    companion object {
        private const val SOFT_ANGLE = 0f
    }
}