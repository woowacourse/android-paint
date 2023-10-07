package woowacourse.paint.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

enum class PaletteShape {
    RECTANGLE,
    CIRCLE,
    ;

    fun draw(
        canvas: Canvas,
        width: Float,
        height: Float,
        sizePercentage: Float = DEFAULT_PERCENTAGE,
        paint: Paint = DEFAULT_PAINT,
    ) {
        when (this) {
            RECTANGLE -> canvas.drawRect(
                width - width * sizePercentage,
                height - height * sizePercentage,
                width * sizePercentage,
                height * sizePercentage,
                paint,
            )

            CIRCLE -> canvas.drawCircle(
                width / 2F,
                height / 2F,
                width / 2F * sizePercentage,
                paint,
            )
        }
    }

    companion object {
        private const val DEFAULT_PERCENTAGE = 0.8F

        private val DEFAULT_PAINT = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = Color.CYAN
        }
    }
}
