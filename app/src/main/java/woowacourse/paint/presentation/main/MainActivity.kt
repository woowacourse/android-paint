package woowacourse.paint.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.palette.PaletteAdapter
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var paintStrokeWidth = 0f
    private var paintColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setListener()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val colors = BrushColorUiModel.values().toList()
        val adapter = PaletteAdapter(colors) { paintColor = colors[it].color }
        viewBinding.rvColor.adapter = adapter
    }

    private fun setListener() {
        viewBinding.btnColorChange.setOnClickListener {
            viewBinding.customCanvas.changeColor(paintColor)
        }

        viewBinding.btnThicknessChange.setOnClickListener {
            viewBinding.customCanvas.changeStrokeWidth(paintStrokeWidth)
        }

        viewBinding.rangeSlider.addOnChangeListener { _, value, _ ->
            paintStrokeWidth = value
        }
    }
}
