package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityDrawingBinding
import woowacourse.paint.model.DefaultColor
import woowacourse.paint.model.DrawingMode
import woowacourse.paint.model.PaintColor
import woowacourse.paint.ui.colorpicker.ColorPickerAdapter
import woowacourse.paint.ui.drawingmode.DrawingModeAdapter

class DrawingActivity : AppCompatActivity(), ColorPickerHandler, DrawingModeHandler {
    private val binding: ActivityDrawingBinding by lazy {
        ActivityDrawingBinding.inflate(layoutInflater)
    }
    private val drawingBoard by lazy {
        binding.customViewDrawingBoard
    }
    private val rangeSlider by lazy {
        binding.rangeSliderDrawingBrushSizeController
    }
    private val colorPickerAdapter by lazy {
        ColorPickerAdapter(this)
    }
    private val colorList by lazy { getColorsFromResource() }
    private val drawingModeAdapter by lazy {
        DrawingModeAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupDrawingBoard()
        setupStrokeSizeController()
        setupColorPickerAdapter()
        setupDrawingModeAdapter()
    }

    override fun onColorClicked(color: Int) {
        drawingBoard.setPaintColor(color)
    }

    override fun onDrawingModeClicked(mode: DrawingMode) {
        drawingBoard.setDrawingMode(mode)
    }

    private fun getColorsFromResource(): List<PaintColor> {
        return DefaultColor.getList().map { color ->
            PaintColor(getColor(color.colorInt))
        }
    }

    private fun setupDrawingBoard() {
        drawingBoard.setPaintColor(colorList.first().color)
        drawingBoard.setPaintStrokeSize(MIN_STROKE_SIZE)
        drawingBoard.setDrawingMode(DrawingMode.PEN)
    }

    private fun setupStrokeSizeController() {
        rangeSlider.valueFrom = MIN_STROKE_SIZE
        rangeSlider.valueTo = MAX_STROKE_SIZE
        rangeSlider.values = mutableListOf(MIN_STROKE_SIZE)
        rangeSlider.stepSize = STROKE_STEP_SIZE
        rangeSlider.addOnChangeListener(RangeSlider.OnChangeListener { _, value, _ ->
            drawingBoard.setPaintStrokeSize(value)
        })
    }

    private fun setupColorPickerAdapter() {
        colorPickerAdapter.submitList(colorList)
        binding.colorPickerAdapter = colorPickerAdapter
    }

    private fun setupDrawingModeAdapter() {
        binding.rvDrawingModePicker.adapter = drawingModeAdapter
    }

    companion object {
        private const val MIN_STROKE_SIZE = 5f
        private const val MAX_STROKE_SIZE = 100f
        private const val STROKE_STEP_SIZE = 5f
    }
}
