package woowacourse.paint.paintboard.toolbar.dialog

import android.content.Context
import android.graphics.Paint.Style
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.DialogShapeToolBinding
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.paintboard.common.Brush.ShapeBrush
import woowacourse.paint.paintboard.common.Shape
import woowacourse.paint.paintboard.pentool.PenColorAdapter
import woowacourse.paint.paintboard.pentool.PenColors

class ShapeToolDialog(
    private val context: Context,
    minWidth: Int,
    maxWidth: Int,
    shape: Shape,
    private val getBrush: (Brush) -> Unit,
) : ToolDialog(context) {
    override val binding = DialogShapeToolBinding.inflate(LayoutInflater.from(context))
    private lateinit var adapter: PenColorAdapter
    private val penColors = PenColors()
    private val selectedColor: Int
        get() = ContextCompat.getColor(context, penColors.currentPenColor)
    override var brush = ShapeBrush(selectedColor, ((minWidth + maxWidth) / 2), Style.FILL, shape)

    init {
        initView()
        initAdapter()
        setRangeSlider(minWidth, maxWidth)
        setPenWidth()
        setSwitch()
    }

    private fun initAdapter() {
        adapter = PenColorAdapter { selectColor(it) }
        binding.rvBrushToolShapeColors.adapter = adapter
        adapter.submitList(penColors.selectColor(PenColors.DEFAULT_COLOR_POSITION))
    }

    private fun selectColor(position: Int) {
        adapter.submitList(penColors.selectColor(position))
        brush = brush.copy(color = selectedColor).also(getBrush)
    }

    private fun setRangeSlider(minWidth: Int, maxWidth: Int) {
        binding.rsBrushToolShapeWidth.valueFrom = minWidth.toFloat()
        binding.rsBrushToolShapeWidth.valueTo = maxWidth.toFloat()
        binding.rsBrushToolShapeWidth.stepSize = 1f
        binding.rsBrushToolShapeWidth.values = listOf(((minWidth + maxWidth) / 2).toFloat())
    }

    private fun setPenWidth() {
        binding.rsBrushToolShapeWidth.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                brush = brush.copy(width = value.toInt()).also(getBrush)
            },
        )
    }

    private fun setSwitch() {
        binding.switchBrushToolShapeFillType.setOnCheckedChangeListener { _, isChecked ->
            binding.rsBrushToolShapeWidth.visibility = if (isChecked) View.GONE else View.VISIBLE
            val filType: Style = if (isChecked) Style.FILL else Style.STROKE
            brush = brush.copy(fillType = filType).also(getBrush)
        }
    }
}
