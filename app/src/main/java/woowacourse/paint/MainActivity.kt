package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.MyColor

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setPaintColor()
        setPaintWidth()
        setBrushType()
    }

    private fun setPaintColor() {
        binding.btnColorRed.setOnClickListener {
            binding.canvasView.currentPaint.color = MyColor.RED
        }

        binding.btnColorOrange.setOnClickListener {
            binding.canvasView.currentPaint.color = MyColor.ORANGE
        }

        binding.btnColorYellow.setOnClickListener {
            binding.canvasView.currentPaint.color = MyColor.YELLOW
        }

        binding.btnColorGreen.setOnClickListener {
            binding.canvasView.currentPaint.color = MyColor.GREEN
        }

        binding.btnColorBlue.setOnClickListener {
            binding.canvasView.currentPaint.color = MyColor.BLUE
        }
    }

    private fun setPaintWidth() {
        binding.rangeSlider.valueFrom = 0.0f
        binding.rangeSlider.valueTo = 100.0f

        binding.rangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.canvasView.currentPaint.strokeWidth = value
            },
        )
    }

    private fun setBrushType() {
        binding.btnChangeToPen.setOnClickListener {
            binding.canvasView.changeBrushType(BrushType.PEN)
        }
        binding.btnChangeToRectangle.setOnClickListener {
            binding.canvasView.changeBrushType(BrushType.RECTANGLE)
        }
        binding.btnChangeToCircle.setOnClickListener {
            binding.canvasView.changeBrushType(BrushType.CIRCLE)
        }
        binding.btnChangeToEraser.setOnClickListener {
            binding.canvasView.changeBrushType(BrushType.ERASER)
        }
    }

    private fun initBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
