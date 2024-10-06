package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.brush.Circle
import woowacourse.paint.brush.Eraser
import woowacourse.paint.brush.Pen
import woowacourse.paint.brush.Rect
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRangeSlider()
        setupColorGroupListener()

        binding.brushGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.btn_pen -> binding.paintView.changeBrush(Pen::class)
                R.id.btn_rect -> binding.paintView.changeBrush(Rect::class)
                R.id.btn_circle -> binding.paintView.changeBrush(Circle::class)
                R.id.btn_erase -> binding.paintView.changeBrush(Eraser::class)
            }

        }
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
