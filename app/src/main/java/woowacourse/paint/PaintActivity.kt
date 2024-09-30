package woowacourse.paint

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityPaintBinding

class PaintActivity : AppCompatActivity() {
    private var _binding: ActivityPaintBinding? = null
    private val binding get() = _binding!!

    private val rangeSlider: RangeSlider by lazy { binding.rangeSliderThickness }
    private val viewModel: PaintViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBinding()
        setupRangeSlider()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                true
            }

            MotionEvent.ACTION_MOVE -> {
                true
            }

            MotionEvent.ACTION_UP -> {
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }

    private fun setupRangeSlider() {
        rangeSlider.valueFrom = 0.0f
        rangeSlider.valueTo = 100.0f

        rangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                viewModel.changeThickness(value.toInt())
            },
        )
    }
}
