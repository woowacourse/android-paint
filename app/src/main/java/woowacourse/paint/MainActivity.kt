package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.brush.CircleBrush
import woowacourse.paint.brush.LineBrush
import woowacourse.paint.brush.RectangleBrush
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
        setupRangeSlider()
        setupButton()
        setupToolbar()
        setupPaletteView()
    }

    private fun setupView() {
        val margin = (resources.displayMetrics.widthPixels - 1024) / 2
        if (margin > 0) {
            binding.startGuideline.setGuidelineBegin(margin)
            binding.endGuideline.setGuidelineEnd(margin)
        }
    }

    private fun setupRangeSlider() {
        with(binding) {
            rangeSlider.setValues(10f)

            rangeSlider.addOnChangeListener { _, value, _ ->
                canvasView.setStrokeWidth(value)
            }
        }
    }

    private fun setupButton() {
        with(binding) {
            lineBrushButton.setOnClickListener {
                canvasView.changeBrush(LineBrush())
            }
            rectBrushButton.setOnClickListener {
                canvasView.changeBrush(RectangleBrush())
            }
            circleBrushButton.setOnClickListener {
                canvasView.changeBrush(CircleBrush())
            }
            eraserButton.setOnClickListener {
                canvasView.eraserMode()
            }
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupPaletteView() {
        with(binding) {
            paletteView.onColorSelected = { canvasView.setColor(it) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.undoAction -> {
                binding.canvasView.undo()
                true
            }

            R.id.redoAction -> {
                binding.canvasView.redo()
                true
            }

            R.id.clearAction -> {
                binding.canvasView.clear()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
