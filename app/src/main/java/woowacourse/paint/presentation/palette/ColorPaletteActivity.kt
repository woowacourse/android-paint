package woowacourse.paint.presentation.palette

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityColorPaletteBinding
import woowacourse.paint.presentation.paint.PaintView

class ColorPaletteActivity :
    AppCompatActivity(),
    ColorPaletteListener,
    BrushListener,
    PaintHistoryListener {
    private val binding: ActivityColorPaletteBinding by lazy {
        ActivityColorPaletteBinding.inflate(layoutInflater)
    }
    private val paintView: PaintView by lazy { binding.paintView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        initializeThicknessRangeSlider()
    }

    private fun initializeBinding() {
        setContentView(binding.root)
        binding.paintHistoryListener = this
        binding.brushListener = this
        binding.colorPaletteListener = this
    }

    private fun initializeThicknessRangeSlider() {
        binding.rangeSliderThickness.addOnChangeListener { _, value, _ ->
            paintView.changeOvalSize(value)
        }
    }

    override fun onSelectColor(colorUiModel: ColorUiModel) {
        paintView.changePaintColor(colorUiModel)
    }

    override fun onChangeBrushType(brushType: BrushType) {
        paintView.changeBrushType(brushType)
    }

    override fun onEmpty() {
        paintView.empty()
    }

    override fun onUndo() {
        paintView.undo()
    }

    override fun onRedo() {
        paintView.redo()
    }
}
