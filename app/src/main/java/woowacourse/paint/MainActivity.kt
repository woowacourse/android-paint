package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ColorPaletteHandler {
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val adapter: ColorPaletteAdapter by lazy { ColorPaletteAdapter(colorPaletteHandler = this) }
    private lateinit var drawingBoard: DrawingBoard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDrawingBoard()
        setupColorPalette()
        setupStrokeWidthRangeSlider()
    }

    override fun onColorChangeClicked(color: Int) {
        drawingBoard.setupColor(color)
    }

    private fun setupDrawingBoard() {
        drawingBoard = binding.customViewMainDrawingBoard
    }

    private fun setupColorPalette() {
        binding.rvMainColorPalette.adapter = adapter
    }

    private fun setupStrokeWidthRangeSlider() {
        binding.rangeSliderMainStrokeWidth.addOnChangeListener { _, value, _ ->
            drawingBoard.setupStrokeWidth(strokeWidth = value)
        }
    }
}
