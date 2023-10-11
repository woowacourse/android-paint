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

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val paintingColorAdapter: PaintingColorAdapter by lazy {
        binding.rvColors.setHasFixedSize(true)
        PaintingColorAdapter(paintingColors, ::setPaintColor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpStrokeWidthControllerView()
        setUpPaintingColorControllerView()
        initEraseButtonClick()
        initUndoButtonClick()
        initPenButtonClick()
        initRectangleButtonClick()
        initCircleButtonClick()
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

    private fun setPaintColor(color: PaintingColor) {
        binding.paintingView.changePaintColor(colorRes = color.colorRes)
    }

    private fun initEraseButtonClick() {
        binding.buttonErase.setOnClickListener {
            binding.paintingView.setPaintingType(PaintingType.ERASER)
        }
    }

    private fun initUndoButtonClick() {
        binding.buttonUndo.setOnClickListener {
            binding.paintingView.undo()
        }
    }

    private fun initPenButtonClick() {
        binding.buttonPen.setOnClickListener {
            binding.paintingView.setPaintingType(PaintingType.LINE)
        }
    }

    private fun initRectangleButtonClick() {
        binding.buttonRectangle.setOnClickListener {
            binding.paintingView.setPaintingType(PaintingType.RECTANGLE)
        }
    }

    private fun initCircleButtonClick() {
        binding.buttonCircle.setOnClickListener {
            binding.paintingView.setPaintingType(PaintingType.CIRCLE)
        }
    }

    companion object {
        private const val MINIMUM_STROKE = 0.0f
        private const val MAXIMUM_STROKE = 100.0f
    }
}
