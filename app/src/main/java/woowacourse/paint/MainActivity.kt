package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.paint.BrushType.Companion.changeBrushType
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
        changeTool(BrushType.PEN, visibleState = View.VISIBLE)
    }

    override fun onRectangleClicked() {
        changeTool(BrushType.RECTANGLE, visibleState = View.GONE)
    }

    override fun onCircleClicked() {
        changeTool(BrushType.CIRCLE, visibleState = View.GONE)
    }

    override fun onEraserClicked() {
        changeTool(BrushType.ERASER, visibleState = View.GONE)
    }

    private fun changeTool(
        brushType: BrushType,
        visibleState: Int,
    ) {
        changeBrushType(brushType)
        drawingBoard.setupStyle()
        binding.rangeSliderMainStrokeWidth.visibility = visibleState
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
