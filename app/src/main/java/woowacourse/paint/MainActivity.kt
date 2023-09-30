package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.DrawingCanvas.Companion.DEFAULT_STROKE_WIDTH
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.ColorBox

class MainActivity : AppCompatActivity(), OnColorClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val adapter by lazy {
        ColorAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRangeSlider()
        setAdapter()
        setListener()
        setObserver()
    }

    private fun setupRangeSlider() {
        binding.rangeSlider.setValues(DEFAULT_STROKE_WIDTH)
    }

    private fun setAdapter() {
        binding.rvColor.adapter = adapter
    }

    private fun setListener() {
        binding.rangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.cvPainter.setStroke(value)
            },
        )

        binding.btnColorChange.setOnClickListener {
            if (binding.rvColor.isVisible) {
                binding.rvColor.visibility = View.GONE
            } else {
                binding.rvColor.visibility = View.VISIBLE
            }
        }

        binding.btnStrokeChange.setOnClickListener {
            if (binding.rangeSlider.isVisible) {
                binding.rangeSlider.visibility = View.GONE
            } else {
                binding.rangeSlider.visibility = View.VISIBLE
            }
        }
    }

    private fun setObserver() {
        viewModel.colors.observe(this) {
            adapter.setColors(it)
        }
    }

    override fun onColorClick(colorBox: ColorBox) {
        binding.cvPainter.setColor(colorBox.color)
        viewModel.setColorsSelected(colorBox)
    }
}
