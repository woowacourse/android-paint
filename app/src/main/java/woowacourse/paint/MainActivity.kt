package woowacourse.paint

import android.os.Bundle
import android.view.View
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

        initBinding()
        initPalette()
        setupRangeSliderListener()
        setupChangePaintColorListener()
        setupChangeStrokeSizeListener()
        setupChangeBrushListener()
    }

    private fun initBinding() {
        binding.vm = mainViewModel
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.canvasMain.setPaintColor(mainViewModel.paintColor)
        binding.canvasMain.setStrokeSize(mainViewModel.strokeSize)
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun initPalette() {
        binding.rvMain.adapter = PaletteAdapter(mainViewModel.paletteColor) { color ->
            mainViewModel.paintColor = color
            binding.canvasMain.setPaintColor(color)
        }
        binding.rvMain.setHasFixedSize(true)
    }

    private fun setupRangeSliderListener() {
        binding.rsMain.addOnChangeListener { _, value, _ ->
            mainViewModel.strokeSize = value
            binding.canvasMain.setStrokeSize(value)
        }
    }

    private fun setupChangePaintColorListener() {
        binding.btnMainChangeColor.setOnClickListener {
            binding.rvMain.changeVisibility()
        }
    }

    private fun setupChangeStrokeSizeListener() {
        binding.btnMainChangeStrokeSize.setOnClickListener {
            binding.rsMain.changeVisibility()
        }
    }

    private fun setupChangeBrushListener() {
        binding.btnMainChangeBrush.setOnClickListener {
            binding.llMainBrush.changeVisibility()
        }
    }

    private fun View.changeVisibility() {
        visibility = when (visibility) {
            View.VISIBLE -> View.GONE
            else -> View.VISIBLE
        }
    }
}
