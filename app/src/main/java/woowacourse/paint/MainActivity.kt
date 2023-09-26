package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding.rsMain) {
            valueFrom = 0f
            valueTo = 10f

            addOnChangeListener(
                RangeSlider.OnChangeListener { _, value, _ ->
                    binding.canvasMain.setStrokeSize(value)
                },
            )
        }
    }
}
