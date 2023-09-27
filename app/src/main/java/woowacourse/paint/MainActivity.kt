package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
            R.color.red,
            R.color.orange,
            R.color.yellow,
            R.color.green,
            R.color.blue
        ).map { getColor(it) }

        binding.adapter = PaletteAdapter(colors) { color: Int ->
            binding.customCanvas.changeColor(color)
        }
    }

    private fun initRangeSlider() {
        binding.rangeSlider.valueFrom = 0.0f
        binding.rangeSlider.valueTo = 100.0f
        binding.rangeSlider.addOnChangeListener { _, value, _ ->
            binding.customCanvas.changeWidth(value)
        }
    }
}
