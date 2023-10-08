package woowacourse.paint.view.palette

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.common.rangeSlider
import woowacourse.paint.view.palette.color.ColorScrollView
import woowacourse.paint.view.palette.color.PaletteColor
import woowacourse.paint.view.palette.shape.PaletteShape
import woowacourse.paint.view.palette.shape.ShapeScrollView

class PaletteView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var paintPropertyChangeListener: OnPaintPropertyChangeListener? = null
    var selectedPaintThickness: Float = THICKNESS_SIZE_UNIT
    var selectedPaletteColor: PaletteColor = PaletteColor.values().first()
    private var selectedPaletteShape: PaletteShape = PaletteShape.values().first()

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
            PaletteMode.BRUSH -> {
                colorScrollView.isVisible = true
                painterThicknessRangeSlider.isVisible = true
                shapeScrollView.isVisible = false
            }

            PaletteMode.SHAPE -> {
                colorScrollView.isVisible = true
                shapeScrollView.isVisible = true
                painterThicknessRangeSlider.isVisible = false
            }

            PaletteMode.ERASER -> {
                painterThicknessRangeSlider.isVisible = true
                colorScrollView.isVisible = false
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
