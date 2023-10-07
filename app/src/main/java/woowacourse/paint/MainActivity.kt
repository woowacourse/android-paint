package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.DrawingCanvas.Companion.DEFAULT_STROKE_WIDTH
import woowacourse.paint.adapter.BrushAdapter
import woowacourse.paint.adapter.ColorAdapter
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.listener.OnBrushClickListener
import woowacourse.paint.listener.OnColorClickListener
import woowacourse.paint.model.ColorBox
import woowacourse.paint.model.PaintBrush

class MainActivity : AppCompatActivity(), OnColorClickListener, OnBrushClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val colorAdapter by lazy {
        ColorAdapter(this)
    }

    private val brushAdapter by lazy {
        BrushAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRangeSlider()
        setAdapter()
        setListener()
        setObserver()
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
        binding.rangeSlider.setValues(DEFAULT_STROKE_WIDTH)
    }

    private fun setAdapter() {
        binding.rvColor.adapter = colorAdapter
        binding.rvBrush.adapter = brushAdapter
    }

    private fun setListener() {
        binding.rangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.cvPainter.setStroke(value)
            },
        )

        binding.btnColorChange.setOnClickListener {
            binding.rvColor.isVisible = !binding.rvColor.isVisible
        }

        binding.btnStrokeChange.setOnClickListener {
            binding.rangeSlider.isVisible = !binding.rangeSlider.isVisible
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

        viewModel.paintBrush.observe(this) {
            brushAdapter.submitList(it)
            binding.cvPainter.setBrush(it.first { paintBrush -> paintBrush.isSelected })
        }
    }

    override fun onColorClick(colorBox: ColorBox) {
        viewModel.setColorsSelected(colorBox)
    }

    override fun onBrushClick(paintBrush: PaintBrush) {
        viewModel.setBrushesSelected(paintBrush)
    }
}
