package woowacourse.paint.ui.glocanvas

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityGloCanvasBinding

@AndroidEntryPoint
class GloCanvasActivity : AppCompatActivity() {
    private val binding: ActivityGloCanvasBinding by lazy {
        ActivityGloCanvasBinding.inflate(
            layoutInflater,
        )
    }
    private val viewModel: GloCanvasViewModel by viewModels()
    private lateinit var paintColorPaletteAdapter: PaintColorPaletteAdapter
    private lateinit var drawingToolSettingsAdapter: DrawingToolSettingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupViewModel()
        setupDrawingToolSettings()
        setupThicknessSettings()
        setupPaintColorPalette()
        binding.vPaintBoard.setupPalette(viewModel.palette)
    }

    private fun setupViewModel() {
        viewModel.drawingTools.observe(this) { drawingToolSettingsAdapter.updateDrawingTools(it) }
        viewModel.paintColors.observe(this) { paintColorPaletteAdapter.updateColors(it) }
    }

    private fun setupDrawingToolSettings() {
        drawingToolSettingsAdapter = DrawingToolSettingsAdapter {
            viewModel.selectDrawingTool(it)
        }
        binding.rvDrawingToolSettings.adapter = drawingToolSettingsAdapter
        binding.rvDrawingToolSettings.itemAnimator = null
    }

    private fun setupThicknessSettings() {
        binding.rsThicknessSettings.isTickVisible = false
        binding.rsThicknessSettings.addOnChangeListener { _, value, _ ->
            viewModel.setThickness(value)
        }
    }

    private fun setupPaintColorPalette() {
        paintColorPaletteAdapter = PaintColorPaletteAdapter {
            viewModel.selectPaintColor(it)
        }
        binding.rvPaintColorPalette.adapter = paintColorPaletteAdapter
        binding.rvPaintColorPalette.itemAnimator = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_glo_canvas, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.go_to_previous_drawing -> {
                binding.vPaintBoard.goToPreviousDrawing()
            }

            R.id.go_to_next_drawing -> {
                binding.vPaintBoard.goToNextDrawing()
            }

            R.id.new_canvas -> {
                binding.vPaintBoard.setNewCanvas()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
