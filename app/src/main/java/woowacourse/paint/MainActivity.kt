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

        binding.apply {
            rangeSlider.setValues(10f)

            rangeSlider.addOnChangeListener { _, value, _ ->
                canvasView.setStrokeWidth(value)
            }

            paletteView.onColorSelected = { canvasView.setColor(it) }
        }
    }
}
