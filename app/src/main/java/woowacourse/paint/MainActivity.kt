package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.PaintingColor

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val paintingColorList = PaintingColor.entries
    private val paintingColorAdapter by lazy {
        PaintingColorAdapter(paintingColorList) { color ->
            binding.customView.changeColor(
                ContextCompat.getColor(
                    this,
                    color.colorRes,
                ),
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvColors.adapter = paintingColorAdapter
        binding.rangeSlider
            .apply {
                values = listOf(5f)
                valueFrom = 1f
                valueTo = 20f
            }.addOnChangeListener { _, value, _ ->
                binding.customView.changeStrokeWidth(value)
            }
    }
}
