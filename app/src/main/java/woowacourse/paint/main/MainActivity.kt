package woowacourse.paint.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.main.adapter.ColorAdapter
import woowacourse.paint.model.DrawMode

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewModel()

        setColorsRecyclerview()
        setBrushSizeListener()
        setDrawModeListener()
    }

    private fun setViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setColorsRecyclerview() {
        binding.rvColors.apply {
            adapter = ColorAdapter {
                viewModel.setBrushColor(it)
            }
            setHasFixedSize(true)
        }
    }

    private fun setBrushSizeListener() {
        binding.sliderBrushSize.addOnChangeListener { _, value, _ ->
            viewModel.setBrushSize(value)
        }
    }

    private fun setDrawModeListener() {
        binding.rgDrawMode.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rbBrush.id -> viewModel.setDrawMode(DrawMode.BRUSH)
                binding.rbSquare.id -> viewModel.setDrawMode(DrawMode.SQUARE)
                binding.rbCircle.id -> viewModel.setDrawMode(DrawMode.CIRCLE)
                binding.rbEraser.id -> viewModel.setDrawMode(DrawMode.ERASER)
            }
        }
    }
}
