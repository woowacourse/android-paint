package woowacourse.paint

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        observeViewModel()
        observeStrokeWidth()
    }

    private fun initViewModel() {
        binding.vm = viewModel
    }

    private fun initBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeViewModel() {
        viewModel.selectedColor.observe(
            this,
            Observer { color ->
                binding.drawingView.setPaintColor(color = color.getColor(this))
            },
        )

        viewModel.selectedShape.observe(
            this,
            Observer { shapeType ->
                binding.drawingView.setShapeType(shapeType)
            },
        )
    }

    private fun observeStrokeWidth() {
        binding.sbPaintWidth.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean,
                ) {
                    binding.drawingView.setPaintWidth(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            },
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
