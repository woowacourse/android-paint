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
            rangeSlider.valueFrom = 0.0f
            rangeSlider.valueTo = 100.0f
            rangeSlider.setValues(10f)

            rangeSlider.addOnChangeListener { _, value, _ ->
                customView.setStrokeWidth(value)
            }
        }
    }
}
