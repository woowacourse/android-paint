package woowacourse.paint.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class PaletteView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var paintPropertyChangeListener: OnPaintPropertyChangeListener? = null

    init {
        orientation = VERTICAL
        val painterThicknessRangeSlider = rangeSlider(context) { value ->
            setPaintThickness(value * 100 * THICKNESS_SIZE_UNIT)
        }
        addView(painterThicknessRangeSlider)
        addView(ColorScrollView.create(context, ::setPaintColor))
    }

    private fun setPaintThickness(paintThickness: Float) {
        paintPropertyChangeListener?.onStrokeThicknessChanged(paintThickness)
    }

    private fun setPaintColor(paintColor: PaletteColor) {
        paintPropertyChangeListener?.onColorSelected(paintColor)
    }

    fun setOnPropertyChangeListener(listener: OnPaintPropertyChangeListener) {
        paintPropertyChangeListener = listener
    }

    companion object {
        private const val THICKNESS_SIZE_UNIT = 0.5F
    }

    interface OnPaintPropertyChangeListener {
        fun onColorSelected(paintColor: PaletteColor)
        fun onStrokeThicknessChanged(paintThickness: Float)
    }
}
