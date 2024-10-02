package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityDrawingBinding
import woowacourse.paint.model.PaintColor
import woowacourse.paint.ui.colorpicker.ColorPickerAdapter


class DrawingActivity : AppCompatActivity(), ColorPickerHandler {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupDrawingBoard()
        setupStrokeSizeController()
        setupColorPickerAdapter()
    }

    override fun onColorClicked(color: Int) {
        drawingBoard.setPaintColor(color)
    }

    private fun getColorsFromResource(): List<PaintColor> {
        val redColorInt = getColor(R.color.red)
        val orangeColorInt = getColor(R.color.orange)
        val yellowColorInt = getColor(R.color.yellow)
        val greenColorInt = getColor(R.color.green)
        val blueColorInt = getColor(R.color.blue)

        return listOf(
            PaintColor(redColorInt),
            PaintColor(orangeColorInt),
            PaintColor(yellowColorInt),
            PaintColor(greenColorInt),
            PaintColor(blueColorInt),
        )
    }

    private fun setupStrokeSizeController() {
        rangeSlider.valueFrom = MIN_STROKE_SIZE
        rangeSlider.valueTo = MAX_STROKE_SIZE
        rangeSlider.values = mutableListOf(MIN_STROKE_SIZE)
        rangeSlider.addOnChangeListener(RangeSlider.OnChangeListener { _, value, _ ->
            drawingBoard.setPaintStrokeSize(value)
        })
    }

    private fun setupColorPickerAdapter() {
        colorPickerAdapter.submitList(colorList)
        binding.colorPickerAdapter = colorPickerAdapter
    }

    private fun setupDrawingBoard() {
        drawingBoard.setPaintColor(colorList.first().color)
        drawingBoard.setPaintStrokeSize(MIN_STROKE_SIZE)
    }

    companion object {
        private const val MIN_STROKE_SIZE = 5f
        private const val MAX_STROKE_SIZE = 100f
    }
}
