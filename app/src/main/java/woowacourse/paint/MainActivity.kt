package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.util.MyColor

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setPaintColor()
        setPaintWidth()
    }

    private fun setPaintColor() {
        binding.btnColorRed.setOnClickListener {
            binding.customView.currentPaint.color = MyColor.RED
        }

        binding.btnColorOrange.setOnClickListener {
            binding.customView.currentPaint.color = MyColor.ORANGE
        }

        binding.btnColorYellow.setOnClickListener {
            binding.customView.currentPaint.color = MyColor.YELLOW
        }

        binding.btnColorGreen.setOnClickListener {
            binding.customView.currentPaint.color = MyColor.GREEN
        }

        binding.btnColorBlue.setOnClickListener {
            binding.customView.currentPaint.color = MyColor.BLUE
        }
    }

    private fun setPaintWidth() {
        binding.rangeSlider.valueFrom = 0.0f
        binding.rangeSlider.valueTo = 100.0f

        binding.rangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.customView.currentPaint.strokeWidth = value
            },
        )
    }

    private fun initBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
