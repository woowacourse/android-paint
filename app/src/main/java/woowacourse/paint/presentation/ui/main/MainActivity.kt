package woowacourse.paint.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.slider.RangeSlider
import kotlinx.coroutines.launch
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.domain.model.BrushType
import woowacourse.paint.presentation.ui.main.brushTypes.BrushTypesAdapter
import woowacourse.paint.presentation.ui.main.brushTypes.ItemBrushType
import woowacourse.paint.presentation.ui.main.colors.ColorsAdapter
import woowacourse.paint.presentation.ui.main.colors.ItemColor
import woowacourse.paint.presentation.ui.model.BrushColorModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initCollect()
        initView()
    }

    private fun initCollect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.brush.collect { brush ->
                    binding.cvCanvas.changeBrush(brush)
                }
            }
        }
    }

    private fun initView() {
        initRangeSliderBrushWidth()
        initColorAdapter()
        initBrushTypesAdapter()
        initUndo()
        initRedo()
        initClear()
    }

    private fun initRangeSliderBrushWidth() {
        val brush = viewModel.brush.value
        val rangeSliderClickListener = RangeSlider.OnChangeListener { _, value, _ ->
            viewModel.changeBrushWidth(value)
        }
        with(binding.rsBrushWidth) {
            valueFrom = brush.minWidth
            valueTo = brush.maxWidth
            setValues(brush.width)
            addOnChangeListener(rangeSliderClickListener)
        }
    }

    private fun initColorAdapter() {
        val itemColors = BrushColorModel.values()
            .map { brushColor ->
                ItemColor(brushColor, viewModel::changeBrushColor)
            }
        binding.rvBrushColors.adapter = ColorsAdapter(itemColors)
        binding.rvBrushColors.setHasFixedSize(true)
    }

    private fun initBrushTypesAdapter() {
        val itemBrushTypes = BrushType.values()
            .map { brushType ->
                ItemBrushType(brushType, viewModel::changeBrushType)
            }
        binding.rvBrushTypes.adapter = BrushTypesAdapter(itemBrushTypes)
        binding.rvBrushTypes.setHasFixedSize(true)
    }

    private fun initUndo() {
        binding.ivClear.setOnClickListener {
            binding.cvCanvas.clear()
        }

        binding.ivUndo.setOnClickListener {
            binding.cvCanvas.undo()
        }

        binding.ivRedo.setOnClickListener {
            binding.cvCanvas.redo()
        }
    }

    private fun initRedo() {
    }

    private fun initClear() {
    }
}
