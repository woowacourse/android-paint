package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnColorClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRangeSlider()
        setAdapter()
        setListener()
    }

    private fun setupRangeSlider() {
        binding.rangeSlider.valueFrom = 0.0f
        binding.rangeSlider.valueTo = 100.0f
    }

    private fun setAdapter() {
        binding.rvColor.adapter = ColorAdapter(paintColors, this)
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

    override fun onColorClick(color: Int) {
        binding.cvPainter.setColor(color)
    }

    companion object {
        private val paintColors = listOf(
            R.color.red,
            R.color.orange,
            R.color.yellow,
            R.color.green,
            R.color.blue,
        )
    }
}
