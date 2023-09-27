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

        setupSizeSelector()
    }

    private fun setupSizeSelector() {
        binding.rsSize.apply {
            setValues(PaintBoard.DEFAULT_SIZE)
            setupSizeChangeListener()
        }
    }

    private fun RangeSlider.setupSizeChangeListener() {
        addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.pbPaintBoard.changeSize(value)
            },
        )
    }
}
