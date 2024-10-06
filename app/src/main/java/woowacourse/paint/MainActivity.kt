package woowacourse.paint

import android.os.Bundle
import android.view.View
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

        setUpBrushListener()
        setupRangeSlider()
        setupColorGroupListener()
        clearCanvas()
    }

    private fun setUpBrushListener() {
        with(binding) {
            brushGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.btn_pen -> changePen()
                    R.id.btn_rect -> changeRect()
                    R.id.btn_circle -> changeCircle()
                    R.id.btn_erase -> changeErase()
                }
            }
        }
    }

    private fun ActivityMainBinding.changeErase() {
        paintView.changeBrush(Eraser::class)
        colorGroup.visibility = View.INVISIBLE
        rangeSlider.visibility = View.VISIBLE
    }

    private fun ActivityMainBinding.changeCircle() {
        paintView.changeBrush(Circle::class)
        rangeSlider.visibility = View.INVISIBLE
        colorGroup.visibility = View.VISIBLE
    }

    private fun ActivityMainBinding.changeRect() {
        paintView.changeBrush(Rect::class)
        rangeSlider.visibility = View.INVISIBLE
        colorGroup.visibility = View.VISIBLE
    }

    private fun ActivityMainBinding.changePen() {
        paintView.changeBrush(Pen::class)
        rangeSlider.visibility = View.VISIBLE
        colorGroup.visibility = View.VISIBLE
    }

    private fun setupRangeSlider() {
        binding.rangeSlider.setValues(CanvasView.DEFAULT_BRUSH_SIZE)

        binding.rangeSlider.addOnChangeListener { _, brushWidth, _ ->
            binding.paintView.changeBrushWidth(brushWidth)
        }
    }

    private fun setupColorGroupListener() {
        with(binding) {
            colorGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.btn_red -> paintView.changePaintColor(ColorType.RED)
                    R.id.btn_orange -> paintView.changePaintColor(ColorType.ORANGE)
                    R.id.btn_yellow -> paintView.changePaintColor(ColorType.YELLOW)
                    R.id.btn_green -> paintView.changePaintColor(ColorType.GREEN)
                    R.id.btn_blue -> paintView.changePaintColor(ColorType.BLUE)
                }
            }
        }
    }

    private fun clearCanvas() {
        binding.btnClear.setOnClickListener {
            binding.paintView.clear()
        }
    }
}
