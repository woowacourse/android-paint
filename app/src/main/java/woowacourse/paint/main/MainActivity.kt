package woowacourse.paint.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.R
import woowacourse.paint.customview.BrushColor
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.main.adapter.BrushColorPaletteAdapter
import woowacourse.paint.main.model.BrushColorBox

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

        val newBrushColorBoxes = getUpdatedBrushColorBoxes(clickedBrushColorBox)
        brushColorPaletteAdapter.submitList(newBrushColorBoxes)
        viewModel.updateBrushColor(clickedBrushColorBox.brushColor.colorRes)
        viewModel.updateBrushColorBoxes(newBrushColorBoxes)
    }

    private fun getUpdatedBrushColorBoxes(clickedBrushColorBox: BrushColorBox): List<BrushColorBox> =
        BrushColor.getColorBoxes(clickedBrushColorBox.brushColor.colorRes)

    private fun View.toggleVisibleOrGone() {
        this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
}
