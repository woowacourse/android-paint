package woowacourse.paint.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.PaintColor

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setColorsRecyclerview()
        setDefaultBrush()
        setBrushSizeListener()
    }

    private fun setColorsRecyclerview() {
        binding.rvColors.apply {
            adapter = ColorAdapter {
                binding.ctvPaintBoard.setBrushColor(it)
            }
            setHasFixedSize(true)
        }
    }

    private fun setDefaultBrush() {
        binding.rangeSliderBrushSize.values = listOf(DEFAULT_BRUSH_SIZE)
        binding.ctvPaintBoard.apply {
            setBrushSize(DEFAULT_BRUSH_SIZE)
            setBrushColor(getColor(PaintColor.values().first().colorRes))
        }
    }

    private fun setBrushSizeListener() {
        binding.rangeSliderBrushSize.addOnChangeListener { _, value, _ ->
            binding.ctvPaintBoard.setBrushSize(value)
        }
    }

    companion object {
        private const val DEFAULT_BRUSH_SIZE = 50f
    }
}
