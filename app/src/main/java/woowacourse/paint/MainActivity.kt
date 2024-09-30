package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.Brush
import woowacourse.paint.ui.PaintBoard


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var brush: Brush
    private lateinit var paintBoard: PaintBoard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initPaintBoard()
        initBrush()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        paintBoard = binding.canvas
    }

    private fun initBrush() {
        brush = Brush()
        binding.canvas.setBrush(brush)
    }

    private fun initPaintBoard() {
        binding.rangeSlider.addOnChangeListener { _, value, _ ->
            val newBrush = brush.changeWidth(value)
            binding.canvas.setBrush(newBrush)
        }
    }
}
