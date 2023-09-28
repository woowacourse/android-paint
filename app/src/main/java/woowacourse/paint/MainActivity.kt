package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

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
        binding.rangeSlider.valueFrom = 0.0f
        binding.rangeSlider.valueTo = 100.0f
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
