package woowacourse.paint

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.palette.PaletteAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initColorButtons()
        initWidthRangeSlider()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.color = Color.RED
        binding.width = 3F
    }

    private fun initColorButtons() {
        val colors = listOf(
            Color.RED, Color.argb(255, 255, 128, 0), Color.YELLOW, Color.GREEN, Color.BLUE
        )

        binding.mainPalettes.adapter = PaletteAdapter(colors) {
            binding.color = it
        }
    }

    private fun initWidthRangeSlider() {
        binding.mainWidthRangeSlider.valueFrom = 0F
        binding.mainWidthRangeSlider.valueTo = 100F
        binding.mainWidthRangeSlider.addOnChangeListener { _, value, _ ->
            binding.width = value
        }
    }
}
