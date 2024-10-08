package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushType
import woowacourse.paint.ui.PaintBoard
import woowacourse.paint.ui.PaletteAction
import woowacourse.paint.ui.PaletteAdapter

class MainActivity : AppCompatActivity(), PaletteAction {
    private lateinit var binding: ActivityMainBinding
    private lateinit var paintBoard: PaintBoard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initPaintBoard()
        initBrushType()
        initPalette()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        paintBoard = binding.canvas
    }

    private fun initPaintBoard() {
        binding.rangeSlider.addOnChangeListener { _, value, _ ->
            binding.canvas.setBrushWidth(value)
        }
    }

    private fun initBrushType() {
        binding.rgDrawingTools.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_pen -> paintBoard.setBrushType(BrushType.PENCIL)
                R.id.rb_square -> paintBoard.setBrushType(BrushType.SQUARE)
                R.id.rb_circle -> paintBoard.setBrushType(BrushType.CIRCLE)
                R.id.rb_eraser -> paintBoard.setBrushType(BrushType.ERASER)
            }
        }
    }

    private fun initPalette() {
        val palette = resources.getIntArray(R.array.palette_colors).toList()
        binding.rvPalette.adapter = PaletteAdapter(palette, this)
    }

    override fun onColorSelected(color: Int) {
        binding.canvas.setBrushColor(color)
    }
}
