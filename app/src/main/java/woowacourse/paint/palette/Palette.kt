package woowacourse.paint.palette

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.R
import woowacourse.paint.databinding.PaletteBinding
import woowacourse.paint.palette.Tool.LINE

class Palette(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private lateinit var onSelectedColorIdChangedListener: (Int) -> Unit
    private lateinit var onStrokeWidthChangedListener: (Float) -> Unit
    private lateinit var onToolChangedListener: (Tool) -> Unit

    private val binding: PaletteBinding

    @ColorRes
    private var selectedColorId: Int = R.color.black

    private var strokeWidth: Float = 10f

    private var currentTool: Tool = LINE

    init {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.palette, this, true)
        initStrokeWidthSelector()
        initColorSelectorRecyclerView()
        initStrokeWidthSelectorListener()
    }

    private fun initStrokeWidthSelector() {
        binding.rangeSliderStrokeWidthSelector.apply {
            values = listOf(10f)
            valueFrom = 10.0f
            valueTo = 40.0f
        }
    }

    fun setOnSelectedColorIdChangedListener(listener: (Int) -> Unit) {
        onSelectedColorIdChangedListener = listener
    }

    fun setStrokeWidthChangedListener(listener: (Float) -> Unit) {
        onStrokeWidthChangedListener = listener
    }

    fun setToolChangedListener(listener: (Tool) -> Unit) {
        onToolChangedListener = listener
    }

    private fun initColorSelectorRecyclerView() {
        binding.rvColorSelector.adapter = ConcatAdapter(
            initColorSelectorAdapter(),
            initToolSelectorAdapter()
        )
    }

    private fun initColorSelectorAdapter() = ColorSelectorAdapter { colorId ->
        selectedColorId = colorId
        if (::onSelectedColorIdChangedListener.isInitialized) {
            onSelectedColorIdChangedListener(colorId)
        }
    }

    private fun initToolSelectorAdapter() = ToolSelectorAdapter { tool ->
        currentTool = tool
        if (::onToolChangedListener.isInitialized) {
            onToolChangedListener(tool)
        }
    }

    private fun initStrokeWidthSelectorListener() {
        binding.rangeSliderStrokeWidthSelector.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                strokeWidth = value
                if (::onStrokeWidthChangedListener.isInitialized) {
                    onStrokeWidthChangedListener(strokeWidth)
                }
            },
        )
    }
}
