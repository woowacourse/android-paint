package woowacourse.paint

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MarginLayoutParamsCompat
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.PaletteColor
import woowacourse.paint.model.pen.Pen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSlider()
        initColorPalette()
    }

    private fun initSlider() {
        binding.slider.value = Pen.DEFAULT_WIDTH
        binding.slider.valueTo = Pen.MAX_WIDTH
        binding.slider.valueFrom = Pen.MIN_WIDTH
        binding.slider.stepSize = Pen.WIDTH_STEP

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            binding.paintView.pen.width = value
        }
    }

    private fun initColorPalette() {
        PaletteColor.values().forEachIndexed { index, paletteColor ->
            val container = binding.colorContainer
            val view = TextView(this@MainActivity)
            val params = ViewGroup.MarginLayoutParams(240, 240)
            if (index != PaletteColor.values().lastIndex) {
                MarginLayoutParamsCompat.setMarginEnd(params, 40)
            }

            view.layoutParams = params
            view.setBackgroundColor(Color.parseColor(paletteColor.hexCode))

            view.setOnClickListener {
                binding.paintView.pen.paletteColor = paletteColor
            }

            container.addView(view)
        }
    }
}
