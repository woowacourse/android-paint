package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.PaintBoard.Companion.DEFAULT_STROKE_WIDTH
import woowacourse.paint.databinding.ActivityPaintBinding

class PaintActivity : AppCompatActivity() {
    private var _binding: ActivityPaintBinding? = null
    private val binding get() = _binding!!

    private val rangeSlider: RangeSlider by lazy { binding.rangeSliderThickness }
    private val paintBoard: PaintBoard by lazy { binding.paintBoard }
    private val viewModel: PaintViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBinding()
        setupRangeSlider()
        setupObserving()
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
        rangeSlider.valueFrom = STROKE_WIDTH_START_VALUE
        rangeSlider.valueTo = STROKE_WIDTH_END_VALUE
        rangeSlider.setValues(DEFAULT_STROKE_WIDTH)

        rangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                paintBoard.setPaintStrokeWidth(value)
            },
        )
    }

    private fun setupObserving() {
        viewModel.color.observe(this) { colorResId ->
            paintBoard.setPaintColor(colorResId)
        }
    }

    companion object {
        private const val STROKE_WIDTH_START_VALUE = 0.0f
        private const val STROKE_WIDTH_END_VALUE = 100.0f
    }
}
