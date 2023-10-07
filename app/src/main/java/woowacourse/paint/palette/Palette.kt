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

    private lateinit var onSelectedColorIdChangedListener: (Int) -> Unit
    private lateinit var onStrokeWidthChangedListener: ((Float) -> Unit)

    private val binding: PaletteBinding

    @ColorRes
    private var selectedColorId: Int = R.color.black

    private var strokeWidth: Float = 10f

    init {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.palette, this, true)
        initStrokeWidthSelector()
        initColorSelectorRecyclerView()
        initStrokeWidthSelectorListener()
    }

    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        onSelectedColorIdChangedListener: (Int) -> Unit,
        onStrokeWidthChangedListener: (Float) -> Unit,
    ) : this(context, attrs) {
        this.onSelectedColorIdChangedListener = onSelectedColorIdChangedListener
        this.onStrokeWidthChangedListener = onStrokeWidthChangedListener
    }

    private fun initStrokeWidthSelector() {
        binding.rangeSliderStrokeWidthSelector.apply {
            values = listOf(10f)
            valueFrom = 10.0f
            valueTo = 40.0f
        }
    }

    private fun initColorSelectorRecyclerView() {
        binding.rvColorSelector.adapter = ColorSelectorAdapter {
            selectedColorId = it
            onSelectedColorIdChangedListener(it)
        }
    }

    private fun initStrokeWidthSelectorListener() {
        binding.rangeSliderStrokeWidthSelector.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                strokeWidth = value
                onStrokeWidthChangedListener(strokeWidth)
            },
        )
    }
}
