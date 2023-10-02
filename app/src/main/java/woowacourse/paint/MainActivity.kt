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
        initPaintColorsRecyclerView()
        initPaintWidthRangeSlider()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initPaintColorsRecyclerView() {
        val colors = listOf(
            R.color.red, R.color.orange, R.color.yellow, R.color.green, R.color.blue
        ).map { getColor(it) }

        binding.adapter = PaletteAdapter(colors) { color: Int ->
            binding.mainCustomCanvas.changePaintColor(color)
        }
    }

    private fun initPaintWidthRangeSlider() {
        binding.mainRangeSlider.setValues(DEFAULT_PAINT_WIDTH)

        binding.mainRangeSlider.addOnChangeListener { _, value, _ ->
            binding.mainCustomCanvas.changePaintWidth(value)
        }
    }
}
