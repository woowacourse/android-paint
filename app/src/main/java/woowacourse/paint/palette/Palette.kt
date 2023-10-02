package woowacourse.paint.palette

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.R
import woowacourse.paint.databinding.PaletteBinding

class Palette(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {
    private val binding: PaletteBinding

    @ColorRes
    var selectedColorId: Int = R.color.black

    var strokeWidth: Float = 0f
        get() {
            return field + 10f
        }

    init {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.palette, this, true)
        initColorSelectorRecyclerView()
        initStrokeWidthSelector()
        initStrokeWidthSelectorListener()
    }

    private fun initColorSelectorRecyclerView() {
        binding.rvColorSelector.adapter = ColorSelectorAdapter {
            selectedColorId = it
        }
    }

    private fun initStrokeWidthSelector() {
        binding.rangeSliderStrokeWidthSelector.apply {
            valueFrom = 0.0f
            valueTo = 40.0f
        }
    }

    private fun initStrokeWidthSelectorListener() {
        binding.rangeSliderStrokeWidthSelector.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                strokeWidth = value
            },
        )
    }
}
