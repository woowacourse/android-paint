package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.view.PaletteAdapter

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        PaletteAdapter {
            binding.drawingPaper.currentColor = it.color
        }
    }
    private val viewModel by viewModels<DrawingPaperViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        initDrawingPaper()
        initSlider()
        initAdapter()
        initListeners()
    }

    private fun initDrawingPaper() {
        binding.drawingPaper.currentStrokeWidth = binding.mySlider.sliderPosition
    }

    private fun initSlider() {
        binding.mySlider.setOnSliderChangeListener {
            binding.drawingPaper.currentStrokeWidth = it
        }
    }

    private fun initAdapter() {
        binding.rvPalette.adapter = adapter
        binding.rvPalette.setHasFixedSize(true)
        adapter.submitList(Paint.defaults)
    }

    private fun initListeners() {
        binding.btnUndo.setOnClickListener {
            binding.drawingPaper.undo()
        }
        binding.btnRedo.setOnClickListener {
            binding.drawingPaper.redo()
        }
        binding.btnClearAll.setOnClickListener {
            binding.drawingPaper.clearAll()
        }
    }
}
