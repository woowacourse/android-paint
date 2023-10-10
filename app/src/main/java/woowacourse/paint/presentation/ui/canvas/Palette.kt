package woowacourse.paint.presentation.ui.canvas

import android.graphics.Paint

class Palette(val paint: Paint = Paint()) {

    init {
        initPaint()
    }

    private fun initPaint() {
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
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
}
