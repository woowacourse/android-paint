package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider.OnChangeListener
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnChangeListener()
    }

    private fun setOnChangeListener() {
        binding.rangeSlider.addOnChangeListener(
            OnChangeListener { _, value, _ ->
                binding.Canvas.setMyStrokeWidth(value)
            },
        )
    }
}
