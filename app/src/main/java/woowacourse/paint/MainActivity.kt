package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.painting.PaintingType

class MainActivity : AppCompatActivity() {

    private val paintingColors: List<PaintingColor> by lazy {
        PaintingColor.values().toList()
    }

    private val paintingTypes: List<PaintingType> by lazy {
        PaintingType.values().toList()
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val paintingColorAdapter: PaintingColorAdapter by lazy {
        binding.rvColors.setHasFixedSize(true)
        PaintingColorAdapter(paintingColors, ::setPaintColor)
    }

    private val paintingTypeAdapter: PaintingTypeAdapter by lazy {
        binding.rvPaintingTypes.setHasFixedSize(true)
        PaintingTypeAdapter(paintingTypes, ::setPaintingType)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpStrokeWidthControllerView()
        setUpPaintingColorControllerView()
        setUpPaintingTypeControllerView()
        initUndoButtonClick()
    }

    private fun setUpStrokeWidthControllerView() {
        with(binding.rsStrokeController) {
            valueFrom = MINIMUM_STROKE
            valueTo = MAXIMUM_STROKE
            addOnChangeListener(
                RangeSlider.OnChangeListener { _, value, _ ->
                    binding.paintingView.changePaintWidth(value)
                },
            )
        }
    }

    private fun setUpPaintingColorControllerView() {
        binding.rvColors.adapter = paintingColorAdapter
        binding.rvColors.layoutManager = GridLayoutManager(this, paintingColors.size)
    }

    private fun setUpPaintingTypeControllerView() {
        binding.rvPaintingTypes.adapter = paintingTypeAdapter
        binding.rvPaintingTypes.layoutManager = GridLayoutManager(this, paintingTypes.size)
    }

    private fun setPaintColor(color: PaintingColor) {
        binding.paintingView.changePaintColor(colorRes = color.colorRes)
    }

    private fun setPaintingType(paintingType: PaintingType) {
        binding.paintingView.setPaintingType(paintingType)
    }

    private fun initUndoButtonClick() {
        binding.buttonUndo.setOnClickListener {
            binding.paintingView.undo()
        }
    }

    companion object {
        private const val MINIMUM_STROKE = 0.0f
        private const val MAXIMUM_STROKE = 100.0f
    }
}
