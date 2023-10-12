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
import woowacourse.paint.board.draw.GraphicObjectType
import woowacourse.paint.databinding.PaletteBinding
import woowacourse.paint.palette.Tool.ERASE
import woowacourse.paint.palette.Tool.LINE
import woowacourse.paint.palette.Tool.OVAL
import woowacourse.paint.palette.Tool.RECTANGLE

class Palette(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val binding: PaletteBinding

    @ColorRes
    var selectedColorId: Int = R.color.black

    var strokeWidth: Float = 10f

    var currentTool: Tool = LINE

    val eraseMode: Boolean
        get() = currentTool == ERASE

    val currentGraphicObjectType: GraphicObjectType
        get() = when (currentTool) {
            LINE -> GraphicObjectType.LINE
            ERASE -> GraphicObjectType.LINE
            OVAL -> GraphicObjectType.OVAL
            RECTANGLE -> GraphicObjectType.RECTANGLE
        }

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

    private fun initColorSelectorRecyclerView() {
        binding.rvColorSelector.adapter = ConcatAdapter(
            initColorSelectorAdapter(),
            initToolSelectorAdapter(),
        )
    }

    private fun initColorSelectorAdapter() = ColorSelectorAdapter { colorId ->
        selectedColorId = colorId
    }

    private fun initToolSelectorAdapter() = ToolSelectorAdapter { tool ->
        currentTool = tool
    }

    private fun initStrokeWidthSelectorListener() {
        binding.rangeSliderStrokeWidthSelector.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                strokeWidth = value
            },
        )
    }
}
