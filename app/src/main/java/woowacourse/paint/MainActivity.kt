package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSlider()
    }

    private fun initSlider() {
        binding.slider.value = 5f
        binding.slider.valueTo = 100f
        binding.slider.valueFrom = 1f
        binding.slider.stepSize = 1f

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            binding.paintView.penSize = value
        }
    }
}
