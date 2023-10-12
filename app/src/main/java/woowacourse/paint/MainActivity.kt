package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.canvas.DrawingTool
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.databinding.ItemToolBinding
import woowacourse.paint.utils.toUiModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val canvasView by lazy { binding.cvCanvas }
    private val adapter = ColorsAdapter { model ->
        viewModel.pickColor(model)
    }
    private val toolButtons = mutableListOf<ItemToolBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViewModel()
        setupToolbar()
        setupCanvas()
        setupTools()
        setupColors()
        setupWidthSlider()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        binding.viewModel = viewModel
        viewModel.width.observe(this) { width ->
            canvasView.setupWidth(width)
        }
        viewModel.selectedColor.observe(this) { color ->
            canvasView.setupColor(color)
            viewModel.colors.value?.let { colors ->
                adapter.submitList(colors)
            }
        }
        viewModel.selectedDrawingTool.observe(this) { tool ->
            canvasView.setupTools(tool)
            changeButtonSelectedStatus(tool)
        }
    }

    private fun changeButtonSelectedStatus(drawingTool: DrawingTool) {
        toolButtons.forEach { button ->
            button.root.isSelected = button.drawingTool == drawingTool
        }
        viewModel.setSettingState(PaintChangingState.NOTHING)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.tbPaint)
        binding.ivRedo.setOnClickListener {
            canvasView.redo()
        }
        binding.ivUndo.setOnClickListener {
            canvasView.undo()
        }
        binding.ivClear.setOnClickListener {
            canvasView.eraseAll()
        }
    }

    private fun setupTools() {
        viewModel.drawingTools.forEach { toolAssigned ->
            val toolButtonBinding = ItemToolBinding.inflate(layoutInflater, binding.llTools, true)
            with(toolButtonBinding) {
                onClick = viewModel::pickTool
                drawingTool = toolAssigned
                drawingToolName = getString(toolAssigned.toUiModel().toolNameId)
            }
            toolButtons.add(toolButtonBinding)
        }
    }

    private fun setupCanvas() {
        binding.cvCanvas.initPaint(
            viewModel.width.value ?: MainViewModel.DEFAULT_WIDTH,
            viewModel.selectedColor.value ?: MainViewModel.DEFAULT_SELECTED_COLOR,
        )
    }

    private fun setupColors() {
        binding.rvColors.adapter = adapter
        binding.rvColors.setHasFixedSize(true)
        binding.rvColors.addItemDecoration(SpaceItemDecoration(25))
    }
    private fun setupWidthSlider() {
        binding.rsWidthChanger.valueFrom = MainViewModel.MIN_WIDTH
        binding.rsWidthChanger.valueTo = MainViewModel.MAX_WIDTH
        binding.rsWidthChanger.setValues(viewModel.width.value)
        binding.rsWidthChanger.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                viewModel.pickWidth(value)
            },
        )
    }
}
