package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushTools
import woowacourse.paint.ui.adapter.DrawingModeAdapter
import woowacourse.paint.ui.adapter.PaletteAdapter
import woowacourse.paint.ui.custom.DrawingCanvas.Companion.DEFAULT_PAINT_WIDTH
import woowacourse.paint.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initPaintColorsRecyclerView()
        initPaintWidthRangeSlider()
        initPaintModeRecyclerView()
        initDeleteButton()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initPaintColorsRecyclerView() {
        val colors = resources.getIntArray(R.array.palette_colors).toList()
        binding.paletteAdapter = PaletteAdapter(colors) { color: Int ->
            binding.mainDrawingCanvas.changePaintColor(color)
        }
    }

    private fun initPaintWidthRangeSlider() {
        binding.defaultPaintWidth = DEFAULT_PAINT_WIDTH
        binding.mainBrushWidthSlider.addOnChangeListener { _, value, _ ->
            binding.mainDrawingCanvas.changePaintWidth(value)
        }
    }

    private fun initPaintModeRecyclerView() {
        binding.paintModeAdapter = DrawingModeAdapter(
            BrushTools.values().toList()
        ) { binding.mainDrawingCanvas.changePaintMode(it) }
    }

    private fun initDeleteButton() {
        binding.mainBtnDeleteAll.setOnClickListener {
            binding.mainDrawingCanvas.removeAllDrawings()
        }
    }
}
