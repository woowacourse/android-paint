package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.adapter.BrushAdapter
import woowacourse.paint.adapter.ColorAdapter
import woowacourse.paint.customview.CanvasCallback
import woowacourse.paint.customview.PaintingCanvas.Companion.DEFAULT_STROKE_WIDTH
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushTool
import woowacourse.paint.model.DrawingTool

class MainActivity : AppCompatActivity(), CanvasCallback {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val colorAdapter by lazy {
        ColorAdapter { viewModel.setColorsSelected(it) }
    }

    private val brushAdapter by lazy {
        BrushAdapter { viewModel.setBrushesSelected(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRangeSlider()
        setAdapter()
        setListener()
        setObserver()

        binding.cvPainter.canvasCallback = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_undo -> {
                binding.cvPainter.undoCanvas()
            }

            R.id.menu_redo -> {
                binding.cvPainter.redoCanvas()
            }

            R.id.menu_canvas -> {
                binding.cvPainter.resetCanvas()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRangeSlider() {
        binding.rsWidthChange.setValues(DEFAULT_STROKE_WIDTH)
    }

    private fun setAdapter() {
        binding.rvColor.adapter = colorAdapter
        binding.rvBrush.adapter = brushAdapter
    }

    private fun setListener() {
        binding.rsWidthChange.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.cvPainter.setStroke(value)
            },
        )

        binding.btnColorChange.setOnClickListener {
            binding.rvColor.isVisible = !binding.rvColor.isVisible
        }

        binding.btnStrokeChange.setOnClickListener {
            binding.rsWidthChange.isVisible = !binding.rsWidthChange.isVisible
        }

        binding.btnBrushChange.setOnClickListener {
            binding.rvBrush.isVisible = !binding.rvBrush.isVisible
        }
    }

    private fun setObserver() {
        viewModel.colors.observe(this) {
            colorAdapter.submitList(it)
            binding.cvPainter.setColor(it.first { colorBox -> colorBox.isSelected }.color)
        }

        viewModel.paintBrush.observe(this) { brushList ->
            val selectedBrush = brushList.first { it.isSelected }
            brushAdapter.submitList(brushList)

            with(binding) {
                cvPainter.setBrush(selectedBrush)
                rvColor.isVisible = selectedBrush.brushTool != BrushTool.ERASER
                btnColorChange.isEnabled = selectedBrush.brushTool != BrushTool.ERASER
                rsWidthChange.isVisible =
                    selectedBrush.brushTool !in listOf(BrushTool.CIRCLE, BrushTool.RECTANGLE)
                btnStrokeChange.isEnabled =
                    selectedBrush.brushTool !in listOf(BrushTool.CIRCLE, BrushTool.RECTANGLE)
            }
        }

        viewModel.paintingHistory.observe(this) {
            binding.cvPainter.history = it
        }
    }

    override fun onActionUp(drawingTool: DrawingTool) {
        viewModel.addHistory(drawingTool)
    }

    override fun onUndoHistory() {
        viewModel.undoHistory()
    }

    override fun onRedoHistory() {
        viewModel.redoHistory()
    }

    override fun onClearHistory() {
        viewModel.clearHistory()
    }
}
