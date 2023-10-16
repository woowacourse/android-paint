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
import woowacourse.paint.databinding.ViewDrawingtoolBinding
import woowacourse.paint.model.PaintBrush
import woowacourse.paint.model.Painting

class MainActivity : AppCompatActivity(), CanvasCallback {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val includeBinding: ViewDrawingtoolBinding by lazy {
        binding.viewDrawingTool!!
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
        includeBinding.rsWidthChange.setValues(DEFAULT_STROKE_WIDTH)
    }

    private fun setAdapter() {
        includeBinding.rvColor.adapter = colorAdapter
        includeBinding.rvBrush.adapter = brushAdapter
    }

    private fun setListener() {
        includeBinding.rsWidthChange.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.cvPainter.setStroke(value)
            },
        )

        with(includeBinding) {
            btnColorChange.setOnClickListener {
                rvColor.isVisible = !rvColor.isVisible
            }

            btnStrokeChange.setOnClickListener {
                rsWidthChange.isVisible = !rsWidthChange.isVisible
            }

            btnBrushChange.setOnClickListener {
                rvBrush.isVisible = !rvBrush.isVisible
            }
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

            binding.cvPainter.setBrush(selectedBrush)

            with(includeBinding) {
                rvColor.isVisible = selectedBrush.brushTool != PaintBrush.ERASER
                btnColorChange.isEnabled =
                    selectedBrush.brushTool != PaintBrush.ERASER
                rsWidthChange.isVisible =
                    selectedBrush.brushTool !in listOf(PaintBrush.CIRCLE, PaintBrush.RECTANGLE)
                btnStrokeChange.isEnabled =
                    selectedBrush.brushTool !in listOf(PaintBrush.CIRCLE, PaintBrush.RECTANGLE)
            }
        }

        viewModel.paintingHistory.observe(this) {
            binding.cvPainter.history = it
        }
    }

    override fun onActionUp(drawingTool: Painting) {
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
