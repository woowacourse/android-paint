package woowacourse.paint.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class ColorView private constructor(
    context: Context,
) : View(context) {
    private val paint = Paint()

    private var onColorSelected: (PaletteColor) -> Unit = {}
    private var paletteColor: PaletteColor = PaletteColor.values().first()
        set(value) {
            field = value
            paint.color = paletteColor.color
            invalidate()
        }

    init {
        setOnClickListener {
            onColorSelected(paletteColor)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(COLOR_VIEW_SIZE, COLOR_VIEW_SIZE)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(
            HORIZONTAL_MARGIN,
            TOP_MARGIN,
            width.toFloat() - HORIZONTAL_MARGIN,
            height.toFloat(),
            paint,
        )
    }

    companion object {
        private const val COLOR_VIEW_SIZE = 180
        private const val TOP_MARGIN = 30F
        private const val HORIZONTAL_MARGIN = 20F

        fun create(
            context: Context,
            paletteColor: PaletteColor,
            onColorSelected: (PaletteColor) -> Unit,
        ): ColorView = ColorView(context).also { view ->
            view.paletteColor = paletteColor
            view.onColorSelected = onColorSelected
        }
    }
}
