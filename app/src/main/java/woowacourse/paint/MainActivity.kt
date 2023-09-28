package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.paint.CustomCanvas.Companion.DEFAULT_PAINT_WIDTH
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initColorsRecyclerView()
        initRangeSlider()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initColorsRecyclerView() {
        val colors = listOf(
            R.color.red, R.color.orange, R.color.yellow, R.color.green, R.color.blue
        ).map { getColor(it) }

        binding.adapter = PaletteAdapter(colors) { color: Int ->
            binding.mainCustomCanvas.changeColor(color)
        }
    }

    private fun initRangeSlider() {
        binding.mainRangeSlider.valueFrom = 0.0f
        binding.mainRangeSlider.valueTo = 100.0f

        binding.mainRangeSlider.setValues(DEFAULT_PAINT_WIDTH)

        binding.mainRangeSlider.addOnChangeListener { _, value, _ ->
            binding.mainCustomCanvas.changeWidth(value)
        }
    }
}
