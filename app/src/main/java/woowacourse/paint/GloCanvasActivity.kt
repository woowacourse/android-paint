package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityGloCanvasBinding

class GloCanvasActivity : AppCompatActivity() {
    private val binding: ActivityGloCanvasBinding by lazy {
        ActivityGloCanvasBinding.inflate(
            layoutInflater,
        )
    }
    private val colors = listOf(
        PaintColorModel(R.color.red, true),
        PaintColorModel(R.color.orange, false),
        PaintColorModel(R.color.yellow, false),
        PaintColorModel(R.color.green, false),
        PaintColorModel(R.color.blue, false),
    )
    private val drawingTools = listOf(
        DrawingToolModel(DrawingTool.PEN, true),
        DrawingToolModel(DrawingTool.HIGHLIGHTER, false),
        DrawingToolModel(DrawingTool.ERASER, false),
    )
    private lateinit var paintColorPaletteAdapter: PaintColorPaletteAdapter
    private lateinit var drawingToolSettingsAdapter: DrawingToolSettingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupDrawingToolSettings()
        setupThicknessSettings()
        setupPaintColorPalette()
    }

    private fun setupDrawingToolSettings() {
        drawingToolSettingsAdapter = DrawingToolSettingsAdapter {
            drawingToolSettingsAdapter.updateDrawingTools(
                drawingTools.map { drawingToolModel ->
                    if (drawingToolModel.drawingTool == it) {
                        drawingToolModel.copy(isSelected = true)
                    } else {
                        drawingToolModel.copy(isSelected = false)
                    }
                },
            )
            binding.paintBoard.setDrawingTool(it)
        }
        drawingToolSettingsAdapter.updateDrawingTools(drawingTools)
        binding.rvDrawingToolSettings.adapter = drawingToolSettingsAdapter
        binding.rvDrawingToolSettings.itemAnimator = null
    }

    private fun setupThicknessSettings() {
        binding.rsThicknessSettings.isTickVisible = false
        binding.rsThicknessSettings.addOnChangeListener { _, value, _ ->
            binding.paintBoard.setThickness(value)
        }
    }

    private fun setupPaintColorPalette() {
        paintColorPaletteAdapter = PaintColorPaletteAdapter {
            paintColorPaletteAdapter.updateColors(
                colors.map { paintColor ->
                    if (paintColor.color == it) {
                        paintColor.copy(isSelected = true)
                    } else {
                        paintColor.copy(isSelected = false)
                    }
                },
            )
            binding.paintBoard.setPaintColor(getColor(it))
        }
        paintColorPaletteAdapter.updateColors(colors)
        binding.rvPaintColorPalette.adapter = paintColorPaletteAdapter
        binding.rvPaintColorPalette.itemAnimator = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_glo_canvas, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_canvas -> {
                binding.paintBoard.setNewCanvas()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
