package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRangeSliderListener()
        setupChangeStrokeSizeListener()
    }

    private fun setupRangeSliderListener() {
        binding.rsMain.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                mainViewModel.setStrokeSize(value)
            },
        )
    }

    private fun setupChangeStrokeSizeListener() {
        binding.btnMainChangeStrokeSize.setOnClickListener {
            binding.canvasMain.setStrokeSize(mainViewModel.strokeSize)
        }
    }
}
