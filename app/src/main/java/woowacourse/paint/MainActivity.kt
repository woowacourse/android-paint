package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PaletteListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { PaletteAdapter(PaletteColor.getAllColors(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setRangeSlider()
        setColorPalette()
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

    override fun selectColor(selected: PaletteColor) {
        val color = getColor(selected.colorRes)
        binding.canvas.setStrokeColor(color)
    }
}
