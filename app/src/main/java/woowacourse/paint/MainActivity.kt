package woowacourse.paint

import android.graphics.Paint
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

        val defaultPaint = Paint().apply { color = getColor(Color.values().first().id) }

        setupRangeSlider()
        setupButton(defaultPaint)
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

    private fun setupButton(defaultPaint: Paint) {
        binding.apply {
            lineBrushButton.setOnClickListener {
                canvasView.setBrush(LineBrush(paint = defaultPaint))
            }
            rectBrushButton.setOnClickListener {
                canvasView.setBrush(RectangleBrush(paint = defaultPaint))
            }
            circleBrushButton.setOnClickListener {
                canvasView.setBrush(CircleBrush(paint = defaultPaint))
            }
        }
    }
}
