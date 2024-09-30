package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickPaletteListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var isPaletteVisible = false
    private var isRangeSliderVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.onClickPaletteListener = this

        binding.rangeSlider.valueFrom = 1.0f
        binding.rangeSlider.valueTo = 100.0f

        binding.rangeSlider.addOnChangeListener { _, value, _ ->
            binding.paintView.setThickness(value)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClickChangePaintButton() {
        isPaletteVisible = !isPaletteVisible
        binding.isPaletteVisible = isPaletteVisible
    }

    override fun onClickChangeThicknessButton() {
        isRangeSliderVisible = !isRangeSliderVisible
        binding.isRangeSliderVisible = isRangeSliderVisible
    }

    override fun onClickPaint(color: Int) {
        binding.paintView.setPaint(color)
    }
}
