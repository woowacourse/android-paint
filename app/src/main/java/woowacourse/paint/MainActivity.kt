package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ColorClickListener {

    private lateinit var binding: ActivityMainBinding
    private val brushColorPaletteAdapter: BrushColorPaletteAdapter by lazy {
        BrushColorPaletteAdapter(colorClickListener = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupBrushColorPaletteAdapter()
        setupBrushThicknessRangeSliderChangeListener()
        setupChangeBrushColorPaletteButtonClickListener()
        setupChangeBrushThicknessButtonClickListener()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setupBrushColorPaletteAdapter() {
        binding.rvMainColorPalette.adapter = brushColorPaletteAdapter
        brushColorPaletteAdapter.submitList(getColorBoxes())
    }

    private fun setupBrushThicknessRangeSliderChangeListener() {
        binding.rsMainBrushThickness.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.pvMain.changeBrushThickness(value)
            },
        )
    }

    private fun setupChangeBrushColorPaletteButtonClickListener() {
        binding.btnMainChangeBrushColor.setOnClickListener {
            binding.rvMainColorPalette.toggleVisibleOrGone()
        }
    }

    private fun setupChangeBrushThicknessButtonClickListener() {
        binding.btnMainChangeBrushThickness.setOnClickListener {
            binding.rsMainBrushThickness.toggleVisibleOrGone()
        }
    }

    private fun View.toggleVisibleOrGone() {
        this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
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
