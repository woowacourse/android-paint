package woowacourse.paint.paintboard.toolbar.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.DialogLineToolBinding
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.paintboard.pentool.PenColorAdapter
import woowacourse.paint.paintboard.pentool.PenColors
import woowacourse.paint.paintboard.pentool.PenColors.Companion.DEFAULT_COLOR_POSITION

class LineToolDialog(
    private val context: Context,
    minWidth: Int,
    maxWidth: Int,
    private val getBrush: (Brush.LineBrush) -> Unit,
) : ToolDialog(context) {
    override val binding = DialogLineToolBinding.inflate(LayoutInflater.from(context))
    private lateinit var adapter: PenColorAdapter
    private val penColors = PenColors()
    private val selectedColor: Int
        get() = ContextCompat.getColor(context, penColors.currentPenColor)
    override var brush =
        Brush.LineBrush(selectedColor, ((minWidth + maxWidth) / 2))

    init {
        initView()
        initAdapter()
        setRangeSlider(minWidth, maxWidth)
        setBrushWidth()
    }

    private fun setRangeSlider(minWidth: Int, maxWidth: Int) {
        binding.rsBrushToolWidth.valueFrom = minWidth.toFloat()
        binding.rsBrushToolWidth.valueTo = maxWidth.toFloat()
        binding.rsBrushToolWidth.stepSize = 1f
        binding.rsBrushToolWidth.values = listOf(((minWidth + maxWidth) / 2).toFloat())
    }

    private fun setBrushWidth() {
        binding.rsBrushToolWidth.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                brush = brush.copy(width = value.toInt()).also(getBrush)
            },
        )
    }

    private fun initAdapter() {
        adapter = PenColorAdapter { selectColor(it) }
        binding.rvBrushToolColors.adapter = adapter
        adapter.submitList(penColors.selectColor(DEFAULT_COLOR_POSITION))
    }

    private fun selectColor(position: Int) {
        adapter.submitList(penColors.selectColor(position))
        brush = brush.copy(color = selectedColor).also(getBrush)
    }
}
