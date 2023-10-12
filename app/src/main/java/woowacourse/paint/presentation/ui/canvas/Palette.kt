package woowacourse.paint.presentation.ui.canvas

import android.graphics.Paint

class Palette(val paint: Paint = Paint()) {

    init {
        initPaint()
    }

    private fun initPaint() {
        with(paint) {
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }

    fun changeColor(color: Int): Palette = Palette(
        Paint().apply {
            this.color = color
            this.strokeWidth = paint.strokeWidth
        },
    )

    fun changeWidth(width: Float) = Palette(
        Paint().apply {
            this.strokeWidth = width
            this.color = paint.color
        },
    )

    fun copy() = Palette(
        Paint().apply {
            this.strokeWidth = paint.strokeWidth
            this.color = paint.color
        },
    )
}
