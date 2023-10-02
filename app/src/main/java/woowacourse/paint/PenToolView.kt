package woowacourse.paint

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MarginLayoutParamsCompat
import woowacourse.paint.databinding.ViewPenToolBinding
import woowacourse.paint.model.PaletteColor
import woowacourse.paint.model.pen.BallpointPen
import woowacourse.paint.model.pen.Pen

class PenToolView(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val binding: ViewPenToolBinding =
        ViewPenToolBinding.inflate(LayoutInflater.from(context), this, true)

    var selectedPen: Pen = BallpointPen()
        private set

    init {
        initSlider()
        initColorPalette()
    }

    private fun initSlider() {
        binding.slider.value = Pen.DEFAULT_WIDTH
        binding.slider.valueTo = Pen.MAX_WIDTH
        binding.slider.valueFrom = Pen.MIN_WIDTH
        binding.slider.stepSize = Pen.WIDTH_STEP

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            selectedPen = selectedPen.apply {
                width = value
            }
        }
    }

    private fun initColorPalette() {
        PaletteColor.values().forEachIndexed { index, paletteColor ->
            val container = binding.colorContainer
            val view = TextView(context)
            val params = MarginLayoutParams(240, 240)
            if (index != PaletteColor.values().lastIndex) {
                MarginLayoutParamsCompat.setMarginEnd(params, 40)
            }

            view.layoutParams = params
            view.setBackgroundColor(Color.parseColor(paletteColor.hexCode))

            view.setOnClickListener {
                selectedPen = selectedPen.apply {
                    this.paletteColor = paletteColor
                }
            }

            container.addView(view)
        }
    }
}
