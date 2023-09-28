package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.paint.PaintBoard

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setDefaultBrush()
        setBrushSizeListener()
    }

    private fun setDefaultBrush() {
        binding.rangeSliderBrushSize.values = listOf(PaintBoard.DEFAULT_BRUSH_SIZE)
    }

    private fun setBrushSizeListener() {
        binding.rangeSliderBrushSize.addOnChangeListener { _, value, _ ->
            binding.ctvPaintBoard.setBrushSize(value)
        }
    }
}
