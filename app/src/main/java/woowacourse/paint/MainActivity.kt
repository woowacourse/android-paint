package woowacourse.paint

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MarginLayoutParamsCompat
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.PaletteColor

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
        binding.slider.value = 5f
        binding.slider.valueTo = 100f
        binding.slider.valueFrom = 1f
        binding.slider.stepSize = 1f

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            binding.paintView.penSize = value
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
                binding.paintView.penColor = Color.parseColor(paletteColor.hexCode)
            }

            container.addView(view)
        }
    }
}
