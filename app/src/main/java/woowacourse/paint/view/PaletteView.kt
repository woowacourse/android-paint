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
    var selectedPaletteColor: PaletteColor = PaletteColor.values().first()
    var selectedPaletteShape: PaletteShape = PaletteShape.values().first()

    private val Float.paintThickness: Float
        get() = this * 100 * THICKNESS_SIZE_UNIT
    private val RangeSlider.paintThickness
        get() = values.first().paintThickness

    init {
        orientation = VERTICAL
        addShapeScrollView()
        addThicknessRangeSlider()
        addColorScrollView()
    }

    private fun addShapeScrollView() {
        addView(ShapeScrollView.create(context, ::setPaletteShape))
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
        addView(ColorScrollView.create(context, ::setPaletteColor))
    }

    private fun setPaletteColor(paletteColor: PaletteColor) {
        this.selectedPaletteColor = paletteColor
        paintPropertyChangeListener?.onColorSelected(paletteColor)
    }

    private fun setPaletteShape(paletteShape: PaletteShape) {
        this.selectedPaletteShape = paletteShape
        paintPropertyChangeListener?.onShapeSelected(paletteShape)
    }

    fun setOnPropertyChangeListener(listener: OnPaintPropertyChangeListener) {
        paintPropertyChangeListener = listener
    }

    companion object {
        private const val THICKNESS_SIZE_UNIT = 0.5F
    }

    interface OnPaintPropertyChangeListener {
        fun onColorSelected(paletteColor: PaletteColor)
        fun onShapeSelected(paletteShape: PaletteShape)
        fun onStrokeThicknessChanged(paintThickness: Float)
    }
}
