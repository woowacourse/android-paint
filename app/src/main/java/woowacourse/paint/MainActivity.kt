package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.paint.BrushType.Companion.brushType
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
        changeTool(
            brushType = BrushType.PEN,
            strokeWidthSliderVisibility = View.VISIBLE,
        )
    }

    override fun onRectangleClicked() {
        changeTool(
            brushType = BrushType.RECTANGLE,
            strokeWidthSliderVisibility = View.GONE,
        )
    }

    override fun onCircleClicked() {
        changeTool(
            brushType = BrushType.CIRCLE,
            strokeWidthSliderVisibility = View.GONE,
        )
    }

    override fun onEraserClicked() {
        changeTool(
            brushType = BrushType.ERASER,
            strokeWidthSliderVisibility = View.GONE,
            colorPaletteVisibility = View.GONE,
        )
    }

    private fun changeTool(
        brushType: BrushType,
        strokeWidthSliderVisibility: Int,
        colorPaletteVisibility: Int = View.VISIBLE,
    ) {
        changeBrushType(brushType)
        drawingBoard.setupStyle()
        binding.rangeSliderMainStrokeWidth.visibility = strokeWidthSliderVisibility
        binding.rvMainColorPalette.visibility = colorPaletteVisibility
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
