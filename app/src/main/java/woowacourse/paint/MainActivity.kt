package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRangeSlider()
        setupButton()
        binding.apply {
            paletteView.onColorSelected = { canvasView.setColor(it) }
        }
    }

    private fun setupRangeSlider() {
        binding.apply {
            rangeSlider.setValues(10f)

            rangeSlider.addOnChangeListener { _, value, _ ->
                canvasView.setStrokeWidth(value)
            }
        }
    }

    private fun setupButton() {
        binding.apply {
            lineBrushButton.setOnClickListener {
                canvasView.changeBrush(LineBrush())
            }
            rectBrushButton.setOnClickListener {
                canvasView.changeBrush(RectangleBrush())
            }
            circleBrushButton.setOnClickListener {
                canvasView.changeBrush(CircleBrush())
            }
            eraserButton.setOnClickListener {
                canvasView.eraserMode()
            }
        }
    }
}
