package woowacourse.paint.presentation.palette

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.presentation.paint.PaintView
import woowacourse.paint.presentation.palette.adapter.ColorPaletteAdapter

class ColorPaletteActivity : AppCompatActivity(), ColorPaletteListener {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val paintView: PaintView by lazy { binding.paintView }
    private val colorResIds =
        listOf(R.color.red, R.color.orange, R.color.yellow, R.color.green, R.color.blue)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeLayout()
        initializeColorPalette()
        initializeThicknessRangeSlider()
    }

    private fun initializeLayout() {
        binding.btnChangeColor.setOnClickListener {
            binding.rvColorPalette.reverseVisibility()
        }
        binding.btnChangeThickness.setOnClickListener {
            binding.rangeSliderThickness.reverseVisibility()
        }
    }

    private fun View.reverseVisibility() {
        visibility = if (visibility == VISIBLE) GONE else VISIBLE
    }

    private fun initializeColorPalette() {
        binding.rvColorPalette.adapter = ColorPaletteAdapter(colorResIds, this)
    }

    private fun initializeThicknessRangeSlider() =
        binding.rangeSliderThickness.run {
            stepSize = OVAL_SIZE_INTERVAL
            values = listOf(OVAL_SIZE_MIN)
            valueFrom = OVAL_SIZE_MIN
            valueTo = OVAL_SIZE_MAX
            addOnChangeListener { _, value, _ ->
                paintView.changeOvalSize(value)
            }
        }

    override fun onSelectColor(colorResId: Int) {
        paintView.changePaintColor(colorResId)
    }

    companion object {
        private const val OVAL_SIZE_MIN = 10.0f
        private const val OVAL_SIZE_MAX = 100.0f
        private const val OVAL_SIZE_INTERVAL = 10.0f
    }
}
