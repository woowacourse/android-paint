package woowacourse.paint.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.mapper.toBrushColorUiModel
import woowacourse.paint.palette.PaletteAdapter
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var paintStrokeWidth = 0f
    private lateinit var paintColor: BrushColorUiModel

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
        val adapter = PaletteAdapter(colors) { paintColor = colors[it] }
        viewBinding.rvColor.adapter = adapter
    }

    private fun subscribe() {
        viewModel.brush.observe(this) {
            viewBinding.customCanvas.changeColor(it.brushColor.toBrushColorUiModel())
            viewBinding.customCanvas.changeStrokeWidth(it.brushWidth)
        }
    }

    private fun setListener() {
        viewBinding.btnColorChange.setOnClickListener {
            viewModel.changeBrushColor(paintColor)
        }

        viewBinding.btnThicknessChange.setOnClickListener {
            viewModel.changeBrushWidth(paintStrokeWidth)
        }

        viewBinding.rangeSlider.addOnChangeListener { _, value, _ ->
            paintStrokeWidth = value
        }
    }
}
