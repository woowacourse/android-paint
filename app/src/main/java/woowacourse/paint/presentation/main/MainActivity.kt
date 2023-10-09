package woowacourse.paint.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.mapper.toBrushColorUiModel
import woowacourse.paint.presentation.main.palette.PaletteAdapter
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initRecyclerView()
        subscribe()
        setListener()
    }

    private fun initRecyclerView() {
        val colors = BrushColorUiModel.values().toList()
        val adapter = PaletteAdapter(colors) { viewModel.changeBrushColor(it) }
        viewBinding.rvColor.adapter = adapter
        viewBinding.rvColor.setHasFixedSize(true)
    }

    private fun subscribe() {
        viewModel.brush.observe(this) {
            viewBinding.customCanvas.changeColor(it.brushColor.toBrushColorUiModel())
            viewBinding.customCanvas.changeStrokeWidth(it.brushWidth)
        }
    }

    private fun setListener() {
        viewBinding.rangeSlider.addOnChangeListener { _, value, _ ->
            viewModel.changeBrushWidth(value)
        }
    }
}
