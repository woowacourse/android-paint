package woowacourse.paint

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MarginLayoutParamsCompat
import woowacourse.paint.databinding.ViewPenToolBinding
import woowacourse.paint.model.PaletteColor
import woowacourse.paint.model.pen.Pen

class PenToolView(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val binding: ViewPenToolBinding =
        ViewPenToolBinding.inflate(LayoutInflater.from(context), this, true)

    var selectedPen: Pen = Pen.createDefaultPenInstance()
        private set

    init {
        initSlider()
        initColorPalette()
    }

    fun changeVisibility() {
        if (visibility == View.VISIBLE) {
            binding.root.visibility = View.GONE
            visibility = View.GONE
        } else {
            binding.root.visibility = View.VISIBLE
            visibility = View.VISIBLE
        }
    }

    private fun initSlider() {
        binding.slider.value = Pen.DEFAULT_WIDTH
        binding.slider.valueTo = Pen.MAX_WIDTH
        binding.slider.valueFrom = Pen.MIN_WIDTH
        binding.slider.stepSize = Pen.WIDTH_STEP

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            selectedPen.width = value
        }
    }

    private fun initColorPalette() {
        val container = binding.colorContainer
        val paletteColors = PaletteColor.values().toList()

        repeat(paletteColors.size) { index ->
            val view = createColorView(paletteColors, index)
            container.addView(view)
        }
    }

    private fun createColorView(paletteColors: List<PaletteColor>, index: Int): View {
        val paletteColor = paletteColors[index]
        val view = TextView(context)

        val params = MarginLayoutParams(COLOR_VIEW_WIDTH, COLOR_VIEW_HEIGHT)
        if (index != paletteColors.lastIndex) {
            MarginLayoutParamsCompat.setMarginEnd(params, COLOR_VIEW_MARGIN_BETWEEN)
        }

        view.layoutParams = params
        view.setBackgroundColor(Color.parseColor(paletteColor.hexCode))

        view.setOnClickListener {
            selectedPen.paletteColor = paletteColor
        }

        return view
    }

    companion object {
        private const val COLOR_VIEW_WIDTH = 240
        private const val COLOR_VIEW_HEIGHT = 240
        private const val COLOR_VIEW_MARGIN_BETWEEN = 40
    }
}
