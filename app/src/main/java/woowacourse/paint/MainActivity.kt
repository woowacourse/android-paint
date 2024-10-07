package woowacourse.paint

import android.graphics.Paint
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
        setupToolPanel(style = Paint.Style.STROKE, visibleState = View.VISIBLE)
    }

    override fun onRectangleClicked() {
        drawingBoard.changeBrushType(BrushType.RECTANGLE)
        setupToolPanel(style = Paint.Style.FILL, visibleState = View.GONE)
    }

    override fun onCircleClicked() {
        drawingBoard.changeBrushType(BrushType.CIRCLE)
        setupToolPanel(style = Paint.Style.FILL, visibleState = View.GONE)
    }

    override fun onEraserClicked() {
        drawingBoard.changeBrushType(BrushType.ERASER)
        setupToolPanel(visibleState = View.GONE)
    }

    private fun setupToolPanel(
        style: Paint.Style? = null,
        visibleState: Int,
    ) {
        style?.let {
            drawingBoard.setupStyle(it)
        }
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
