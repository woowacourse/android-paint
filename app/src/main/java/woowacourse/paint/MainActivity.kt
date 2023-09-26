package woowacourse.paint

import android.graphics.Color
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

        initPaletteColor()
        initPalette()
        setupRangeSliderListener()
        setupChangePaintColorListener()
        setupChangeStrokeSizeListener()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.canvasMain.setPaintColor(mainViewModel.paintColor)
        binding.canvasMain.setStrokeSize(mainViewModel.strokeSize)
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun initPaletteColor() {
        mainViewModel.setColors(
            listOf(
                Color.RED,
                getColor(R.color.orange),
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
            ),
        )
    }

    private fun initPalette() {
        binding.rvMain.adapter = PaletteAdapter(mainViewModel.colors) { color ->
            mainViewModel.setPaintColor(color)
        }
        binding.rvMain.setHasFixedSize(true)
    }

    private fun setupRangeSliderListener() {
        binding.rsMain.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                mainViewModel.setStrokeSize(value)
            },
        )
    }

    private fun setupChangePaintColorListener() {
        binding.btnMainChangeColor.setOnClickListener {
            binding.canvasMain.setPaintColor(mainViewModel.paintColor)
        }
    }

    private fun setupChangeStrokeSizeListener() {
        binding.btnMainChangeStrokeSize.setOnClickListener {
            binding.canvasMain.setStrokeSize(mainViewModel.strokeSize)
        }
    }
}
