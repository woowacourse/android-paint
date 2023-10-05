package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.ui.adapter.PaletteAdapter
import woowacourse.paint.ui.custom.Canvas.Companion.DEFAULT_PAINT_WIDTH
import woowacourse.paint.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initPaintColorsRecyclerView()
        initPaintWidthRangeSlider()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initPaintColorsRecyclerView() {
        val colors = resources.getIntArray(R.array.palette_colors).toList()

        binding.mainBrushColorsRecyclerView.adapter = PaletteAdapter(colors) { color: Int ->
            binding.mainCanvas.changePaintColor(color)
        }
    }

    private fun initPaintWidthRangeSlider() {
        binding.defaultPaintWidth = DEFAULT_PAINT_WIDTH
        binding.mainBrushWidthSlider.addOnChangeListener { _, value, _ ->
            binding.mainCanvas.changePaintWidth(value)
        }
    }
}
