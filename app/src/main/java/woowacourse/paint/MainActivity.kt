package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

    private fun initPalette() {
        binding.rvMain.adapter = PaletteAdapter(mainViewModel.paletteColor) { color ->
            mainViewModel.paintColor = color
        }
        binding.rvMain.setHasFixedSize(true)
    }

    private fun setupRangeSliderListener() {
        binding.rsMain.addOnChangeListener { _, value, _ ->
            mainViewModel.strokeSize = value
        }
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
