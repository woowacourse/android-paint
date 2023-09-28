package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ColorClickListener {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val brushColorPaletteAdapter: BrushColorPaletteAdapter by lazy {
        BrushColorPaletteAdapter(colorClickListener = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        observeStrokes()
        observeColorBox()
        setupBrushColorPaletteAdapter()
        setupBrushThicknessRangeSliderChangeListener()
        setupChangeBrushColorPaletteButtonClickListener()
        setupChangeBrushThicknessButtonClickListener()
    }

    override fun onDestroy() {
        viewModel.updateStrokes(binding.pvMain.strokes)
        super.onDestroy()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setupBrushColorPaletteAdapter() {
        binding.rvMainColorPalette.adapter = brushColorPaletteAdapter
        brushColorPaletteAdapter.submitList(getColorBoxes())
    }

    private fun setupBrushThicknessRangeSliderChangeListener() {
        binding.rsMainBrushThickness.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                viewModel.updateBrushThickness(value)
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

    private fun observeStrokes() {
        viewModel.strokes.observe(this) {
            binding.pvMain.setStrokes(it)
        }
    }

    private fun observeColorBox() {
        viewModel.brushColorBoxes.observe(this) {
            brushColorPaletteAdapter.submitList(it)
        }
    }

    override fun onColorClick(clickedBrushColorBox: BrushColorBox) {
        val selectedColorBox = brushColorPaletteAdapter.currentList.firstOrNull { it.isSelected }
        if (selectedColorBox == clickedBrushColorBox) return

        val brushColorBoxes = getUpdatedBrushColorBoxes(clickedBrushColorBox)
        brushColorPaletteAdapter.submitList(brushColorBoxes)
        viewModel.updateBrushColor(clickedBrushColorBox.brushColor.colorRes)
    }

    private fun getUpdatedBrushColorBoxes(clickedBrushColorBox: BrushColorBox) =
        getColorBoxes().map { brushColorBox ->
            if (brushColorBox.brushColor == clickedBrushColorBox.brushColor) {
                return@map brushColorBox.copy(isSelected = !clickedBrushColorBox.isSelected)
            }
            brushColorBox
        }

    private fun getColorBoxes(): List<BrushColorBox> =
        BrushColor.values().map { BrushColorBox(it) }

    private fun View.toggleVisibleOrGone() {
        this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
}
