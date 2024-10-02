package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRangeSlider()
        setupColorGroupListener()
    }

    private fun setupRangeSlider() {
        binding.rangeSlider.setValues(CanvasView.DEFAULT_BRUSH_SIZE)

        binding.rangeSlider.addOnChangeListener { _, brushWidth, _ ->
            binding.paintView.changeBrushWidth(brushWidth)
        }
    }

    private fun setupColorGroupListener() {
        binding.colorGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btn_red -> binding.paintView.changePaintColor(ColorType.RED)
                R.id.btn_orange -> binding.paintView.changePaintColor(ColorType.ORANGE)
                R.id.btn_yellow -> binding.paintView.changePaintColor(ColorType.YELLOW)
                R.id.btn_green -> binding.paintView.changePaintColor(ColorType.GREEN)
                R.id.btn_blue -> binding.paintView.changePaintColor(ColorType.BLUE)
            }
        }
    }
}