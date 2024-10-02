package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.Brush
import woowacourse.paint.ui.PaintBoard
import woowacourse.paint.ui.PaletteAction
import woowacourse.paint.ui.PaletteAdapter

class MainActivity : AppCompatActivity(), PaletteAction {
    private lateinit var binding: ActivityMainBinding
    private lateinit var brush: Brush
    private lateinit var paintBoard: PaintBoard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initBrush()
        initPaintBoard()
        initPalette()
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
            brush = newBrush
        }
    }

    private fun initPalette() {
        val palette = resources.getIntArray(R.array.palette_colors).toList()
        binding.rvPalette.adapter = PaletteAdapter(palette, this)
    }

    override fun onColorSelected(color: Int) {
        val newBrush = brush.changeColor(color)
        binding.canvas.setBrush(newBrush)
        brush = newBrush
    }
}
