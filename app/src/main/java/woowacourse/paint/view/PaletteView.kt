package woowacourse.paint.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.google.android.material.slider.RangeSlider

class PaletteView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var paintPropertyChangeListener: OnPaintPropertyChangeListener? = null
    var selectedPaintThickness: Float = THICKNESS_SIZE_UNIT
    var selectedPaintColor: PaletteColor = PaletteColor.values().first()

    private val Float.paintThickness: Float
        get() = this * 100 * THICKNESS_SIZE_UNIT
    private val RangeSlider.paintThickness
        get() = values.first().paintThickness

    init {
        orientation = VERTICAL
        addThicknessRangeSlider()
        addColorScrollView()
    }

    private fun addThicknessRangeSlider() {
        val painterThicknessRangeSlider = rangeSlider(context) { value ->
            selectedPaintThickness = value.paintThickness
            paintPropertyChangeListener?.onStrokeThicknessChanged(value.paintThickness)
        }
        selectedPaintThickness = painterThicknessRangeSlider.paintThickness

        addView(painterThicknessRangeSlider)
    }

    private fun addColorScrollView() {
        addView(ColorScrollView.create(context, ::setPaintColor))
    }

    private fun setPaintColor(paintColor: PaletteColor) {
        this.selectedPaintColor = paintColor
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
