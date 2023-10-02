package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val paintingColors: List<PaintingColor> by lazy {
        PaintingColor.values().toList()
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val paintingColorAdapter: PaintingColorAdapter by lazy {
        PaintingColorAdapter(paintingColors, ::setPaintColor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpStrokeWidthControllerView()
        setUpPaintingColorControllerView()
    }

    private fun setUpStrokeWidthControllerView() {
        with(binding.rsStrokeController) {
            valueFrom = MINIMUM_STROKE
            valueTo = MAXIMUM_STROKE
            addOnChangeListener(
                RangeSlider.OnChangeListener { _, value, _ ->
                    binding.paintingView.changePaintWidth(value)
                },
            )
        }
    }

    private fun setUpPaintingColorControllerView() {
        binding.rvColors.adapter = paintingColorAdapter
        binding.rvColors.layoutManager = GridLayoutManager(this, paintingColors.size)
    }

    private fun setPaintColor(color: PaintingColor) {
        binding.paintingView.changePaintColor(getColor(color.colorRes))
    }

    companion object {
        private const val MINIMUM_STROKE = 0.0f
        private const val MAXIMUM_STROKE = 100.0f
    }
}
