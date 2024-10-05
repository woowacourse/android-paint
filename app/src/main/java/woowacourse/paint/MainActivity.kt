package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.paint.adapter.ColorPaletteAdapter
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.drawingboard.DrawingBoard

class MainActivity : AppCompatActivity(), ColorPaletteHandler, BrushHandler {
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val adapter: ColorPaletteAdapter by lazy { ColorPaletteAdapter(colorPaletteHandler = this) }
    private val drawingBoard: DrawingBoard by lazy { binding.customViewMainDrawingBoard }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupColorPalette()
        setupStrokeWidthRangeSlider()
        binding.brushHandler = this
    }

    override fun onColorChangeClicked(color: Int) {
        drawingBoard.setupColor(color)
    }

    override fun onPenClicked() {
        drawingBoard.changeBrushType(BrushType.PEN)
        binding.rangeSliderMainStrokeWidth.visibility = View.VISIBLE
    }

    override fun onRectangleClicked() {
        drawingBoard.changeBrushType(BrushType.RECTANGLE)
        binding.rangeSliderMainStrokeWidth.visibility = View.GONE
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
