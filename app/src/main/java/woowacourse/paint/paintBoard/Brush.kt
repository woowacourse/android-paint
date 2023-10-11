package woowacourse.paint.paintBoard

import android.graphics.Paint

data class Brush(
    val paint: Paint = Paint()
) {
    fun changeBrushWidth(width: Float) {
        paint.apply {
            strokeWidth = width
            color = paint.color
        }
    }

    fun changeBrushColor(selectedColor: Int) {
        paint.apply {
            strokeWidth = paint.strokeWidth
            color = selectedColor
        }
    }
}