package woowacourse.paint.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.google.android.material.slider.RangeSlider

class PaletteView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var paintPropertyChangeListener: OnPaintPropertyChangeListener? = null
    var selectedPaintThickness: Float = THICKNESS_SIZE_UNIT
    var selectedPaletteColor: PaletteColor = PaletteColor.values().first()
    var selectedPaletteShape: PaletteShape = PaletteShape.values().first()

    private val shapeScrollView by lazy { ShapeScrollView.create(context, ::setPaletteShape) }
    private val colorScrollView by lazy { ColorScrollView.create(context, ::setPaletteColor) }
    private val painterThicknessRangeSlider by lazy {
        rangeSlider(context) { value ->
            selectedPaintThickness = value.paintThickness
            paintPropertyChangeListener?.onStrokeThicknessChanged(value.paintThickness)
        }
    }

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
        addView(shapeScrollView)
    }

    private fun addThicknessRangeSlider() {
        selectedPaintThickness = painterThicknessRangeSlider.paintThickness
        addView(painterThicknessRangeSlider)
    }

    private fun addColorScrollView() {
        addView(colorScrollView)
    }

    private fun setPaletteColor(paletteColor: PaletteColor) {
        this.selectedPaletteColor = paletteColor
        paintPropertyChangeListener?.onColorSelected(paletteColor)
    }

    private fun setPaletteShape(paletteShape: PaletteShape) {
        this.selectedPaletteShape = paletteShape
        paintPropertyChangeListener?.onShapeSelected(paletteShape)
    }

    fun changePaletteMode(paletteMode: PaletteMode) {
        when (paletteMode) {
            PaletteMode.SHAPE -> {
                colorScrollView.isVisible = true
                shapeScrollView.isVisible = true
                painterThicknessRangeSlider.isVisible = false
            }

            PaletteMode.BRUSH -> {
                colorScrollView.isVisible = true
                painterThicknessRangeSlider.isVisible = true
                shapeScrollView.isVisible = false
            }

            PaletteMode.ERASER -> {
                colorScrollView.isVisible = false
                painterThicknessRangeSlider.isVisible = false
                shapeScrollView.isVisible = false
            }
        }
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
