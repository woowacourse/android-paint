package woowacourse.paint.paintboard.toolbar.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.R
import woowacourse.paint.databinding.DialogEraserToolBinding
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.paintboard.common.EraserState

class EraserToolDialog(
    context: Context,
    minWidth: Int,
    maxWidth: Int,
    private val getBrush: (Brush.EraserBrush) -> Unit,
) : ToolDialog(context) {
    override val binding = DialogEraserToolBinding.inflate(LayoutInflater.from(context))
    override var brush =
        Brush.EraserBrush(((minWidth + maxWidth) / 2), EraserState.PATH)

    init {
        initView()
        setRangeSlider(minWidth, maxWidth)
        setBrushWidth()
        setEraserMode()
    }

    private fun setRangeSlider(minWidth: Int, maxWidth: Int) {
        binding.rsBrushToolEraserArea.valueFrom = minWidth.toFloat()
        binding.rsBrushToolEraserArea.valueTo = maxWidth.toFloat()
        binding.rsBrushToolEraserArea.stepSize = 1f
        binding.rsBrushToolEraserArea.values = listOf(((minWidth + maxWidth) / 2).toFloat())
    }

    private fun setBrushWidth() {
        binding.rsBrushToolEraserArea.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                brush = brush.copy(width = value.toInt()).also(getBrush)
            },
        )
    }

    private fun setEraserMode() {
        binding.rgBrushToolEraser.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbBrushToolEraserPath -> {
                    binding.rsBrushToolEraserArea.visibility = View.GONE
                    brush = brush.copy(mode = EraserState.PATH).also(getBrush)
                }

                R.id.rbBrushToolEraserArea -> {
                    binding.rsBrushToolEraserArea.visibility = View.VISIBLE
                    brush = brush.copy(mode = EraserState.AREA).also(getBrush)
                }
            }
        }
    }
}
