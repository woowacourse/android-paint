package woowacourse.paint.presentation.ui.canvas

import android.graphics.Paint

class Palette(val paint: Paint = Paint()) {

    init {
        initPaint()
    }

    private fun initPaint() {
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
    }

    fun changeColor(color: Int) = Palette(
        Paint().apply { this.color = color },
    )

    fun changeWidth(width: Float) = Palette(
        Paint().apply { this.strokeWidth = width },
    )
}
