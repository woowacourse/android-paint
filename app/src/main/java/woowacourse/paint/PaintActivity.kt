package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.PaintBoard.Companion.DEFAULT_STROKE_WIDTH
import woowacourse.paint.databinding.ActivityPaintBinding

class PaintActivity : AppCompatActivity() {
    private var _binding: ActivityPaintBinding? = null
    private val binding get() = _binding!!

    private val rangeSlider: RangeSlider by lazy { binding.rangeSliderThickness }
    private val paintBoard: PaintBoard by lazy { binding.paintBoard }
    private val adapter: PaintColorAdapter by lazy { PaintColorAdapter(viewModel) }
    private val viewModel: PaintViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBinding()
        setupAdapter()
        setupObserving()
        setupRangeSlider()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }

    private fun setupAdapter() {
        binding.rcvColorPalette.adapter = adapter

        val colorRes = viewModel.colorRes.value ?: return
        submitPaintColors(colorRes)
    }

    private fun setupObserving() {
        viewModel.colorRes.observe(this) { colorRes ->
            val paintColor = getColor(colorRes)
            paintBoard.setPaintColor(paintColor)

            submitPaintColors(colorRes)
        }

        viewModel.strokeWidth.observe(this) { strokeWidth ->
            paintBoard.setPaintStrokeWidth(strokeWidth)
        }
    }

    private fun submitPaintColors(@ColorRes colorRes: Int) {
        val paintColors =
            Color.getColors().map { color ->
                val isChecked = color.colorRes == colorRes
                PaintColor(color, isChecked)
            }
        adapter.submitList(paintColors)
    }

    private fun setupRangeSlider() {
        rangeSlider.apply {
            stepSize = RANGE_STEP
            valueFrom = RANGE_FROM
            valueTo = RANGE_TO
            setValues(DEFAULT_STROKE_WIDTH)
            addOnChangeListener(
                RangeSlider.OnChangeListener { _, value, _ ->
                    viewModel.changeStrokeWidth(value)
                },
            )
        }
    }

    companion object {
        private const val RANGE_STEP = 10.0f
        private const val RANGE_FROM = 10.0f
        private const val RANGE_TO = 100.0f
    }
}
