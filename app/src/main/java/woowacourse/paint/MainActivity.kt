package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PaletteListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { PaletteAdapter(PaletteColor.entries, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setRangeSlider()
        setColorPalette()
        setDrawingMode()
    }

    private fun setRangeSlider() {
        binding.rangeSlider.valueFrom = 1.0f
        binding.rangeSlider.valueTo = 100.0f
        binding.rangeSlider.values = listOf(DrawingView.DEFAULT_STROKE_WIDTH)

        binding.rangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.canvas.setStrokeWidth(value)
            },
        )
    }

    private fun setColorPalette() {
        binding.rvColors.adapter = adapter
    }

    private fun setDrawingMode() {
        binding.radioGroup.check(R.id.radio_line)
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val drawingMode =
                when (checkedId) {
                    R.id.radio_line -> DrawingMode.LINE
                    R.id.radio_rectangle -> DrawingMode.RECTANGLE
                    R.id.radio_circle -> DrawingMode.CIRCLE
                    R.id.radio_eraser -> DrawingMode.ERASE
                    else -> error("유효하지 않는 모드")
                }
            binding.canvas.setDrawingMode(drawingMode)
        }
    }

    override fun selectColor(selected: PaletteColor) {
        val color = getColor(selected.colorRes)
        binding.canvas.setStrokeColor(color)
    }
}
