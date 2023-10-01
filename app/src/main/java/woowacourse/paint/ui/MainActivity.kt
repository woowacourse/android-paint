package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider.OnChangeListener
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.ui.recyclerview.PaintAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnChangeListener()
        setPalette()
    }

    private fun setOnChangeListener() {
        binding.rangeSlider.addOnChangeListener(
            OnChangeListener { _, value, _ ->
                binding.Canvas.setMyStrokeWidth(value)
            },
        )
    }

    private fun setPalette() {
        binding.palette.apply {
            adapter = PaintAdapter(
                listOf(
                    getColor(R.color.red),
                    getColor(R.color.orange),
                    getColor(R.color.yellow),
                    getColor(R.color.green),
                    getColor(R.color.blue),
                ),
                binding.Canvas::setMyStrokeColor,
            )
        }
    }
}
