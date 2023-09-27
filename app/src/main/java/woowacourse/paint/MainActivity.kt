package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ColorClickListener {

    private lateinit var binding: ActivityMainBinding
    private val brushColorPaletteAdapter: BrushColorPaletteAdapter by lazy {
        BrushColorPaletteAdapter(colorClickListener = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvMainColorPalette.adapter = brushColorPaletteAdapter
        brushColorPaletteAdapter.submitList(getColorBoxes())
    }

    override fun onColorClick(clickedBrushColorBox: BrushColorBox) {
        val brushColorBoxes = getColorBoxes().map { brushColorBox ->
            if (brushColorBox.brushColor == clickedBrushColorBox.brushColor) {
                return@map brushColorBox.copy(isSelected = !clickedBrushColorBox.isSelected)
            }
            brushColorBox
        }
        brushColorPaletteAdapter.submitList(brushColorBoxes)
        binding.pvMain.changeBrushColor(clickedBrushColorBox.brushColor)
    }

    private fun getColorBoxes(): List<BrushColorBox> =
        BrushColor.values().map { BrushColorBox(it) }
}
