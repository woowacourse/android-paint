package woowacourse.paint

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.drawingPaper.currentStrokeWidth = binding.mySlider.sliderPosition
        binding.mySlider.setOnSliderChangeListener {
            binding.drawingPaper.currentStrokeWidth = it
        }
        binding.rvPalette.adapter = adapter
        binding.rvPalette.setHasFixedSize(true)
        adapter.submitList(Paint.dummy)
    }
}