package woowacourse.paint.model.pen

import android.graphics.Color
import android.graphics.Paint
import woowacourse.paint.model.PaletteColor

abstract class Pen(
    var paletteColor: PaletteColor = DEFAULT_PALETTE_COLOR,
    var width: Float = DEFAULT_WIDTH,
) {
    val color: Int get() = Color.parseColor(paletteColor.hexCode)
    abstract val style: Paint.Style
    abstract val cap: Paint.Cap
    abstract val join: Paint.Join

    fun createPaint(): Paint {
        return Paint().apply {
            color = this@Pen.color
            style = this@Pen.style
            strokeWidth = this@Pen.width
            strokeCap = this@Pen.cap
            strokeJoin = this@Pen.join
        }
    }

    companion object {
        const val MAX_WIDTH = 100f
        const val MIN_WIDTH = 1f
        const val DEFAULT_WIDTH = 5f
        const val WIDTH_STEP = 1f

        val DEFAULT_PALETTE_COLOR = PaletteColor.default

        fun createDefaultPenInstance(): Pen = BallpointPen()
    }
}
