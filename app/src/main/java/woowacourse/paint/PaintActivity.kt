package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.PaintBoard.Companion.DEFAULT_DRAWING_MODE
import woowacourse.paint.PaintBoard.Companion.DEFAULT_PAINT_COLOR_RES
import woowacourse.paint.PaintBoard.Companion.DEFAULT_STROKE_WIDTH
import woowacourse.paint.action.PaintBoardAction
import woowacourse.paint.adapter.PaintColorAdapter
import woowacourse.paint.adapter.DrawingModeAdapter
import woowacourse.paint.databinding.ActivityPaintBinding
import woowacourse.paint.model.DrawingMode
import woowacourse.paint.model.PaintColor
import woowacourse.paint.uimodel.DrawingModeUiModel
import woowacourse.paint.uimodel.PaintColorUiModel
import woowacourse.paint.viewmodel.PaintViewModel

class PaintActivity : AppCompatActivity() {
    private var _binding: ActivityPaintBinding? = null
    private val binding get() = _binding!!

    private val rangeSlider: RangeSlider by lazy { binding.rangeSliderThickness }
    private val paintBoard: PaintBoard by lazy { binding.paintBoard }
    private val paintColorAdapter: PaintColorAdapter by lazy { PaintColorAdapter(viewModel) }
    private val drawingModeAdapter: DrawingModeAdapter by lazy {
        DrawingModeAdapter(
            viewModel,
        )
    }
    private val viewModel: PaintViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBinding()
        setupAdapter()
        setupObserving()
        setupRangeSlider()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }

    private fun setupAdapter() {
        binding.rcvPaintColor.adapter = paintColorAdapter
        submitPaintColors(DEFAULT_PAINT_COLOR_RES)

        binding.rcvPaintDrawingMode.adapter = drawingModeAdapter
        submitDrawingModes(DEFAULT_DRAWING_MODE)
    }

    private fun setupObserving() {
        viewModel.drawingMode.observe(this) { drawingMode ->
            paintBoard.updateDrawingMode(drawingMode)

            submitDrawingModes(drawingMode)
        }

        viewModel.colorRes.observe(this) { colorRes ->
            val paintColor = getColor(colorRes)
            paintBoard.updatePaintColor(paintColor)

            submitPaintColors(colorRes)
        }

        viewModel.strokeWidth.observe(this) { strokeWidth ->
            paintBoard.updatePaintStrokeWidth(strokeWidth)
        }

        viewModel.boardAction.observe(this) { event ->
            event.getContentIfNotHandled()?.let { action ->
                when (action) {
                    PaintBoardAction.ClearDrawings -> {
                        paintBoard.clearDrawings()
                    }

                    PaintBoardAction.UndoDrawing -> {
                        paintBoard.undoDrawing()
                    }
                }
            }
        }
    }

    private fun submitPaintColors(
        @ColorRes checkedColorRes: Int,
    ) {
        val paintPaintColorUiModels =
            PaintColor.getColors().map { color ->
                val isChecked = color.colorRes == checkedColorRes
                PaintColorUiModel(color, isChecked)
            }
        paintColorAdapter.submitList(paintPaintColorUiModels)
    }

    private fun submitDrawingModes(checkedDrawingMode: DrawingMode) {
        val drawingModes =
            DrawingMode.getDrawingModes().map { drawingMode ->
                val isChecked = drawingMode == checkedDrawingMode
                DrawingModeUiModel(drawingMode, isChecked)
            }
        drawingModeAdapter.submitList(drawingModes)
    }

    private fun setupRangeSlider() {
        rangeSlider.apply {
            stepSize = RANGE_STEP
            valueFrom = RANGE_FROM
            valueTo = RANGE_TO
            setValues(DEFAULT_STROKE_WIDTH)
            addOnChangeListener(
                RangeSlider.OnChangeListener { _, value, _ ->
                    viewModel.changeStrokeWidth(value)
                },
            )
        }
    }

    companion object {
        private const val RANGE_STEP = 10.0f
        private const val RANGE_FROM = 10.0f
        private const val RANGE_TO = 100.0f
    }
}
