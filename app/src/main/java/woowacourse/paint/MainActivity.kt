package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.canvas.Tool
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
        viewModel.selectedTool.observe(this) { tool ->
            canvasView.setupTools(tool)
            changeButtonSelectedStatus(tool)
        }
    }

    private fun changeButtonSelectedStatus(tool: Tool) {
        toolButtons.forEach { button ->
            button.root.isSelected = button.tool == tool
        }
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
        viewModel.tools.forEach { toolAssigned ->
            val toolButtonBinding = ItemToolBinding.inflate(layoutInflater, binding.llTools, true)
            with(toolButtonBinding) {
                onClick = viewModel::pickTool
                tool = toolAssigned
                name = getString(toolAssigned.toUiModel().name)
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
        binding.rvColors.addItemDecoration(SpaceItemDecoration(getSpace()))
    }

    private fun getSpace(): Int {
        val colorWidth = resources.getDimensionPixelSize(R.dimen.color_item_size)
        val display = this.applicationContext?.resources?.displayMetrics
        val deviceWidth = display?.widthPixels

        deviceWidth?.let {
            return (deviceWidth - (colorWidth * 5)) / 4
        }
        return 10
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
